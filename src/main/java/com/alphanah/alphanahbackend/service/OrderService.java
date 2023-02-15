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

    private final int ORDER_FIRSTNAME_MAX_LENGTH = 50;
    private final int ORDER_LASTNAME_MAX_LENGTH = 50;
    private final int ORDER_ADDRESS_MAX_LENGTH = 2048;
    private final int ORDER_PHONE_MAX_LENGTH = 19;

    public Order updateCoupon(UUID creatorUuid, UUID couponUuid) throws AlphanahBaseException {
        Order entity = this.getOrCreateCart(creatorUuid);
        Coupon coupon = Objects.isNull(couponUuid) ? null : couponService.findCoupon(couponUuid);
        entity.setCoupon(coupon);
        return repository.save(entity);
    }

    public List<Order> getAllPaidOrders(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getAllWithNullCreatorUuid();

        List<Order> paidList = new ArrayList<>();
        List<Order> orderList = repository.findAllByCreatorUuid(creatorUuid);
        for (Order paidOrder: orderList) {
            if (paidOrder.getType().equals(OrderType.PAID))
                paidList.add(paidOrder);
        }

        return paidList;
    }

    public Order getOrder(UUID creatorUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw OrderException.getWithNullUuid();

        Optional<Order> optional = repository.findById(uuid);
        if (optional.isEmpty())
            throw OrderException.getNullObject();

        Order order = optional.get();
        if (!order.getType().equals(OrderType.PAID))
            throw OrderException.getNotPaidOrder();

        return order;
    }

    public Order getOrCreateCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getWithNullCreatorUuid();

        Account account = AccountUtils.findAccount(creatorUuid);
        if (account.getCartUuid() == null) {
            return createCart(creatorUuid);
        }

        Optional<Order> optional = repository.findById(account.getCartUuid());
        if (optional.isEmpty())
            throw OrderException.getNullObject();

        Order cart = optional.get();
        if (!cart.getType().equals(OrderType.CART))
            throw OrderException.getNotCartOrder();

        return cart;
    }

    private Order createCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.createWithNullCreatorUuid();

        Account account = AccountUtils.findAccount(creatorUuid);
        if (account.getCartUuid() != null)
            throw OrderException.createDuplicateCart();

        Order entity = new Order();
        entity.setCreatorUuid(creatorUuid);
        entity.setType(OrderType.CART);
        entity.setPayType(PayType.NONE);
        repository.save(entity);
        accountService.updateAwsCognitoField(account, AwsCognitoField.CART_UUID, entity.getUuid().toString());
        return this.getOrCreateCart(creatorUuid);
    }

//    @Transactional
    public Order updateCartToPaid(UUID creatorUUID, PayType payType, String firstname, String lastname, String phone, String address) throws AlphanahBaseException {
        if (Objects.isNull(creatorUUID))
            throw OrderException.updateWithNullCreatorUuid();

        if (Objects.isNull(firstname))
            throw OrderException.updateWithNullFirstname();

        if (Objects.isNull(lastname))
            throw OrderException.updateWithNullLastname();

        if (Objects.isNull(phone))
            throw OrderException.updateWithNullPhone();

        if (Objects.isNull(address))
            throw OrderException.updateWithNullAddress();

        if (firstname.isEmpty())
            throw OrderException.updateWithEmptyFirstname();

        if (lastname.isEmpty())
            throw OrderException.updateWithEmptyLastname();

        if (phone.isEmpty())
            throw OrderException.updateWithEmptyPhone();

        if (address.isEmpty())
            throw OrderException.updateWithEmptyAddress();

        if (firstname.length() > ORDER_FIRSTNAME_MAX_LENGTH)
            throw OrderException.updateWithMaxLengthFirstname();

        if (lastname.length() > ORDER_LASTNAME_MAX_LENGTH)
            throw OrderException.updateWithMaxLengthLastname();

        if (phone.length() > ORDER_PHONE_MAX_LENGTH)
            throw OrderException.updateWithMaxLengthPhone();

        if (address.length() > ORDER_ADDRESS_MAX_LENGTH)
            throw OrderException.updateWithMaxLengthAddress();

        if (Objects.isNull(AccountUtils.findAccount(creatorUUID).getCartUuid()))
            throw OrderException.updateWithNullCartUuid();

        Order entity = this.getOrCreateCart(creatorUUID);

        if (entity.getOrderItems().size() == 0)
            throw OrderException.updateWithEmptyCart();

        entity.setType(OrderType.PAID);
        entity.setPayType(payType);
        entity.setRecipientFirstname(firstname);
        entity.setRecipientLastname(lastname);
        entity.setRecipientPhone(phone);
        entity.setRecipientAddress(address);
        entity.setCheckoutDate(new Date());

        List<OrderItem> orderItemEntityList = new ArrayList<>();
        List<ProductOption> productOptionEntityList = new ArrayList<>();

        // Update All OrderItem to Delivery Status PENDING
        for (OrderItem orderItem: entity.getOrderItems()) {
            orderItem.setPrice(orderItem.getProductOption().getPrice());
            orderItem.setDeliveryStatus(DeliveryStatus.PENDING.toString());
            orderItemEntityList.add(orderItem);

            ProductOption productOption = orderItem.getProductOption();
            if (productOption.getQuantity() < orderItem.getQuantity())
                throw OrderException.updateCartToPaidWithOverStockQuantity();
            productOption.setQuantity(productOption.getQuantity() - orderItem.getQuantity());
            productOptionEntityList.add(productOption);
        }

        Coupon coupon = entity.getCoupon();
        if (!Objects.isNull(coupon) && entity.isCouponActive())
            couponService.updateCouponUsageStatus(coupon.getUuid());
        orderItemRepository.saveAll(orderItemEntityList);
        productOptionRepository.saveAll(productOptionEntityList);
        accountService.updateAwsCognitoField(creatorUUID, AwsCognitoField.CART_UUID, "");
        return repository.save(entity);
    }

}
