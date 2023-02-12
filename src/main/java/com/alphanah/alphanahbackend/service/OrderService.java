package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.enumerate.CognitoField;
import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.enumerate.OrderType;
import com.alphanah.alphanahbackend.enumerate.PayType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.repository.OrderItemRepository;
import com.alphanah.alphanahbackend.repository.OrderRepository;
import com.alphanah.alphanahbackend.repository.ProductOptionRepository;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.aspectj.weaver.ast.Or;
import org.joda.time.DateTime;
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

    private final int ORDER_FIRSTNAME_MAX_LENGTH = 50;
    private final int ORDER_LASTNAME_MAX_LENGTH = 50;
    private final int ORDER_ADDRESS_MAX_LENGTH = 2048;
    private final int ORDER_PHONE_MAX_LENGTH = 19;

    public List<Order> getAllPaidOrders(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getAllWithNullCreatorUuid();

        List<Order> paidList = new ArrayList<>();
        List<Order> orderList = repository.findAllByCreatorUuid(creatorUuid.toString());
        for (Order paidOrder: orderList) {
            if (paidOrder.getType().equals(OrderType.PAID.toString()))
                paidList.add(paidOrder);
        }

        return paidList;
    }

    public Order getOrder(UUID creatorUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getWithNullCreatorUuid();

        if (Objects.isNull(uuid))
            throw OrderException.getWithNullUuid();

        Optional<Order> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw OrderException.getNullObject();

        Order order = optional.get();
        if (!order.getType().equals(OrderType.PAID.toString()))
            throw OrderException.getNotPaidOrder();

        return order;
    }

    public Order getOrCreateCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.getWithNullCreatorUuid();

        Account account = AccountUtils.getAccountWithUuid(creatorUuid);
        if (account.getCartUuid() == null) {
            return createCart(creatorUuid);
        }

        Optional<Order> optional = repository.findById(account.getCartUuid().toString());
        if (optional.isEmpty())
            throw OrderException.getNullObject();

        Order cart = optional.get();
        if (!cart.getType().equals(OrderType.CART.toString()))
            throw OrderException.getNotCartOrder();

        return cart;
    }

    private Order createCart(UUID creatorUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw OrderException.createWithNullCreatorUuid();

        Account account = AccountUtils.getAccountWithUuid(creatorUuid);
        if (account.getCartUuid() != null)
            throw OrderException.createDuplicateCart();

        Order entity = new Order();
        entity.setCreatorUuid(creatorUuid.toString());
        entity.setType(OrderType.CART.toString());
        entity.setPayType(PayType.NONE.toString());
        repository.save(entity);
        accountService.update(account.getUuid(), CognitoField.CART_UUID, entity.getUuid());
        return this.getOrCreateCart(creatorUuid);
    }

//    @Transactional
    public Order updateCartToPaid(UUID creatorUUID, String firstname, String lastname, String phone, String address) throws AlphanahBaseException {
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

        if (Objects.isNull(AccountUtils.getAccountWithUuid(creatorUUID).getCartUuid()))
            throw OrderException.updateWithNullCartUuid();

        Order entity = this.getOrCreateCart(creatorUUID);

        if (entity.getOrderItems().size() == 0)
            throw OrderException.updateWithEmptyCart();

        entity.setType(OrderType.PAID.toString());
        entity.setRecipientFirstname(firstname);
        entity.setRecipientLastname(lastname);
        entity.setRecipientPhone(phone);
        entity.setRecipientAddress(address);
        entity.setPayType(PayType.CARD.toString());
        entity.setCheckoutDate(DateTime.now().toString());

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

        orderItemRepository.saveAll(orderItemEntityList);
        productOptionRepository.saveAll(productOptionEntityList);
        accountService.update(creatorUUID, CognitoField.CART_UUID, "");
        return repository.save(entity);
    }

}
