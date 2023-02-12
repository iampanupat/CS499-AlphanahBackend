package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order.*;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "orders")
public class Order extends BaseEntity implements Comparable<Order> {

    @Column(name = "order_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @Column(name = "order_type", nullable = false)
    private String type;

    @Column(name = "order_checkout_date", length = 29)
    private String checkoutDate;

    @Column(name = "order_pay_type")
    private String payType;

    @Column(name = "order_recipient_firstname")
    private String recipientFirstname;

    @Column(name = "order_recipient_lastname")
    private String recipientLastname;

    @Column(name = "order_recipient_phone")
    private String recipientPhone;

    @Column(name = "order_recipient_address")
    private String recipientAddress;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @Override
    public int compareTo(Order other) {
        DateTime thisDate = DateTime.parse(this.getCheckoutDate());
        DateTime otherDate = DateTime.parse(other.getCheckoutDate());
        return thisDate.compareTo(otherDate) * -1;
    }

    public CartResponseM1 toCartResponseM1(CartResponseM1 response) {
        if (response == null)
            response = new CartResponseM1();

        response.setOrderUuid(this.getUuid());
        response.setCreatorUuid(this.getCreatorUuid());
        response.setOrderType(this.getType());
        return response;
    }

    public CartResponseM2 toCartResponseM2(CartResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new CartResponseM2();

        response = (CartResponseM2) this.toCartResponseM1(response);

        double totalPrice = 0;
        int totalQuantity = 0;
        List<CartItemResponseM2> cartItems = new ArrayList<>();
        if (!Objects.isNull(this.getOrderItems())) {
            for (OrderItem cartItem : this.getOrderItems()) {
                totalPrice += cartItem.getProductOption().getPrice() * cartItem.getQuantity();
                totalQuantity += cartItem.getQuantity();
                cartItems.add(cartItem.toCartItemResponseM2(null));
            }
        }

        response.setTotalPrice(String.valueOf(totalPrice));
        response.setTotalQuantity(String.valueOf(totalQuantity));
        response.setCartItems(cartItems);
        return response;
    }

    public PaidResponseM1 toPaidResponseM1(PaidResponseM1 response) {
        if (response == null)
            response = new PaidResponseM1();

        response = (PaidResponseM1) this.toCartResponseM1(response);
        response.setCheckoutDate(this.getCheckoutDate());
        response.setPayType(this.getPayType());
        DeliveryInformation information = new DeliveryInformation();
        information.setFirstname(this.getRecipientFirstname());
        information.setLastname(this.getRecipientLastname());
        information.setPhone(this.getRecipientPhone());
        information.setAddress(this.getRecipientAddress());
        response.setDeliveryInformation(information);
        return response;
    }

    public PaidResponseM2 toPaidResponseM2(PaidResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new PaidResponseM2();

        response = (PaidResponseM2) this.toPaidResponseM1(response);

        double totalPrice = 0;
        int totalQuantity = 0;
        List<PaidItemResponseM2> paidItems = new ArrayList<>();
        for (OrderItem paidItem: this.getOrderItems()) {
            totalPrice += paidItem.getPrice() * paidItem.getQuantity();
            totalQuantity += paidItem.getQuantity();
            paidItems.add(paidItem.toPaidItemResponseM2(null));
        }

        response.setTotalPrice(String.valueOf(totalPrice));
        response.setTotalQuantity(String.valueOf(totalQuantity));
        response.setOrderItems(paidItems);
        return response;
    }

}
