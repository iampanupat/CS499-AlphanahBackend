package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.*;
import com.alphanah.alphanahbackend.enumerate.AwsCognitoField;
import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.enumerate.OrderType;
import com.alphanah.alphanahbackend.enumerate.PayType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.repository.OrderItemRepository;
import com.alphanah.alphanahbackend.repository.OrderRepository;
import com.alphanah.alphanahbackend.repository.ProductOptionRepository;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CouponService couponService;

    public Order updateCoupon(UUID creatorUuid, String couponCode) throws AlphanahBaseException {
        Order entity = this.findOrCreateCart(creatorUuid);

        Coupon coupon = Objects.isNull(couponCode) ? null : couponService.findCoupon(couponCode);
        if (!coupon.isAvailable())
            throw OrderException.cannotApplyUnavailableCoupon();

        List<Order> orders = repository.findAllByCreatorUuid(creatorUuid);
        for (Order order : orders) {
            if (!Objects.isNull(order.getCoupon()) && order.getCoupon().equals(coupon))
                throw OrderException.cannotApplyAlreadyUsedCoupon();
        }

        entity.setCoupon(coupon);
        return repository.save(entity);
    }

    public List<Order> findAllPaidOrders(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.cannotFindWithNullCreatorUuid();

        List<Order> paidList = new ArrayList<>();
        List<Order> orderList = repository.findAllByCreatorUuid(creatorUuid);
        for (Order paidOrder: orderList) {
            if (paidOrder.getType().equals(OrderType.PAID))
                paidList.add(paidOrder);
        }
        return paidList;
    }

    public Order findPaidOrder(UUID creatorUuid, UUID orderUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.cannotFindWithNullCreatorUuid();

        if (Objects.isNull(orderUuid))
            throw OrderException.cannotFindWithNullOrderUuid();

        Optional<Order> optional = repository.findById(orderUuid);
        if (optional.isEmpty())
            throw OrderException.notFound();

        Order order = optional.get();
        if (!order.getType().equals(OrderType.PAID))
            throw OrderException.notFound();

        return order;
    }

    public Order findOrCreateCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.cannotFindWithNullCreatorUuid();

        Account account = AccountUtils.findAccount(creatorUuid);
        if (account.getCartUuid() == null)
            return createCart(creatorUuid);

        Optional<Order> optional = repository.findById(account.getCartUuid());
        if (optional.isEmpty())
            throw OrderException.notFound();

        Order cart = optional.get();
        if (!cart.getType().equals(OrderType.CART))
            throw OrderException.notFound();

        return cart;
    }

    private Order createCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.cannotCreateWithNullCreatorUuid();

        Account account = AccountUtils.findAccount(creatorUuid);
        if (account.getCartUuid() != null)
            throw OrderException.cannotCreateDuplicateCart();

        Order entity = new Order();
        entity.setCreatorUuid(creatorUuid);
        entity.setType(OrderType.CART);
        entity.setPayType(PayType.NONE);
        repository.save(entity);
        accountService.updateAwsCognitoField(account, AwsCognitoField.CART_UUID, entity.getUuid().toString());
        return this.findOrCreateCart(creatorUuid);
    }

    public Order updateCartToPaid(UUID creatorUUID, PayType payType, String firstname, String lastname, String phone, String address) throws AlphanahBaseException {
        if (Objects.isNull(creatorUUID))
            throw OrderException.cannotUpdateWithNullCreatorUuid();

        if (Objects.isNull(payType))
            throw OrderException.cannotUpdateWithNullPayType();

        if (Objects.isNull(firstname))
            throw OrderException.cannotUpdateWithNullFirstname();

        if (Objects.isNull(lastname))
            throw OrderException.cannotUpdateWithNullLastname();

        if (Objects.isNull(phone))
            throw OrderException.cannotUpdateWithNullPhone();

        if (Objects.isNull(address))
            throw OrderException.cannotUpdateWithNullAddress();

        if (firstname.isEmpty())
            throw OrderException.cannotUpdateWithEmptyFirstname();

        if (lastname.isEmpty())
            throw OrderException.cannotUpdateWithEmptyLastname();

        if (phone.isEmpty())
            throw OrderException.cannotUpdateWithEmptyPhone();

        if (address.isEmpty())
            throw OrderException.cannotUpdateWithEmptyAddress();

        if (firstname.length() > Env.FIRSTNAME_MAX_LENGTH)
            throw OrderException.cannotUpdateWithFirstnameExceedMaxLength();

        if (lastname.length() > Env.LASTNAME_MAX_LENGTH)
            throw OrderException.cannotUpdateWithLastnameExceedMaxLength();

        if (phone.length() > Env.PHONE_MAX_LENGTH)
            throw OrderException.cannotUpdateWithPhoneExceedMaxLength();

        if (address.length() > Env.ADDRESS_MAX_LENGTH)
            throw OrderException.cannotUpdateWithAddressExceedMaxLength();

        if (Objects.isNull(AccountUtils.findAccount(creatorUUID).getCartUuid()))
            throw OrderException.cannotUpdateWithNullCartUuid();

        Order entity = this.findOrCreateCart(creatorUUID);
        if (entity.getOrderItems().size() == 0)
            throw OrderException.cannotUpdateWithEmptyCart();

        entity.setType(OrderType.PAID);
        entity.setPayType(payType);
        entity.setRecipientFirstname(firstname);
        entity.setRecipientLastname(lastname);
        entity.setRecipientPhone(phone);
        entity.setRecipientAddress(address);
        entity.setCheckoutDate(new Date());

        List<OrderItem> orderItemEntityList = new ArrayList<>();
        List<ProductOption> productOptionEntityList = new ArrayList<>();
        for (OrderItem orderItem: entity.getOrderItems()) {
            // Update All OrderItem to Delivery Status PENDING
            orderItem.setPrice(orderItem.getProductOption().getPrice());
            orderItem.setDeliveryStatus(DeliveryStatus.PENDING);
            orderItemEntityList.add(orderItem);

            // Reduce All ProductOption Quantity after Checkout
            ProductOption productOption = orderItem.getProductOption();
            if (productOption.getQuantity() < orderItem.getQuantity())
                throw OrderException.cannotUpdateWithQuantityExceedStock();
            productOption.setQuantity(productOption.getQuantity() - orderItem.getQuantity());
            productOptionEntityList.add(productOption);
        }

        Coupon coupon = entity.getCoupon();
        if (!Objects.isNull(coupon) && entity.isCouponActive())
            couponService.updateCouponUsage(coupon.getCode());
        else
            entity.setCoupon(null);
        orderItemRepository.saveAll(orderItemEntityList);
        productOptionRepository.saveAll(productOptionEntityList);
        accountService.updateAwsCognitoField(creatorUUID, AwsCognitoField.CART_UUID, "");
        return repository.save(entity);
    }

}
