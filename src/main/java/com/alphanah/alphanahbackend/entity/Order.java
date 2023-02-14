package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.enumerate.CouponType;
import com.alphanah.alphanahbackend.enumerate.OrderType;
import com.alphanah.alphanahbackend.enumerate.PayType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order.*;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.*;

@Data
@Entity(name = "orders")
public class Order implements Comparable<Order> {

    @Id
    @Column(name = "order_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "order_creator_uuid", nullable = false, updatable = false)
    private UUID creatorUuid;

    @Column(name = "order_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name = "order_checkout_date")
    private Date checkoutDate;

    @Column(name = "order_pay_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Column(name = "order_recipient_firstname")
    private String recipientFirstname;

    @Column(name = "order_recipient_lastname")
    private String recipientLastname;

    @Column(name = "order_recipient_phone")
    private String recipientPhone;

    @Column(name = "order_recipient_address")
    private String recipientAddress;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "coupon_uuid")
    private Coupon coupon;

    @Override
    public int compareTo(Order other) {
        Date thisDate = Objects.isNull(this.checkoutDate) ? new Date() : this.checkoutDate;
        Date otherDate = Objects.isNull(other.checkoutDate) ? new Date() : other.checkoutDate;
        return thisDate.compareTo(otherDate) * -1;
    }

    public boolean isPaid() {
        return !Objects.isNull(checkoutDate);
    }

    public boolean isCouponActive() {
        boolean priceDiscount = this.calculateRawTotalPrice() != this.calculateTotalPriceWithCoupon();
        boolean deliveryFeeDiscount = this.calculateRawDeliveryFee() != this.calculateDeliveryFeeWithCoupon();
        return priceDiscount || deliveryFeeDiscount;
    }

    private Set<String> merchantUuidSet() {
        Set<String> merchantUuidSet = new HashSet<>();
        for (OrderItem orderItem: orderItems)
            merchantUuidSet.add(orderItem.getProductOption().getProduct().getCreatorUuid());
        return merchantUuidSet;
    }

    private Map<String, Double> merchantTotalPriceMap() {
        Map<String, Double> totalPriceMap = new HashMap<>();
        String merchantUuid;
        double totalPrice;
        for (OrderItem orderItem: orderItems) {
            merchantUuid = orderItem.getProductOption().getProduct().getCreatorUuid();
            totalPrice = totalPriceMap.getOrDefault(merchantUuid, 0.0);
            totalPrice += (this.isPaid() ? orderItem.getPrice() : orderItem.getProductOption().getPrice()) * orderItem.getQuantity();
            totalPriceMap.put(merchantUuid, totalPrice);
        }
        return totalPriceMap;
    }

    public double calculateRawTotalPrice() {
        double finalPrice = 0;
        for (OrderItem orderItem: orderItems)
            finalPrice += (this.isPaid() ? orderItem.getPrice() : orderItem.getProductOption().getPrice()) * orderItem.getQuantity();
        return finalPrice;
    }

    public double calculateTotalPriceWithCoupon() {
        double finalPrice = 0;
        Set<String> merchantUuidSet = this.merchantUuidSet();
        Map<String, Double> totalPriceMap = this.merchantTotalPriceMap();
        for (String merchantUuid: merchantUuidSet) {
            double rawTotalPrice = totalPriceMap.getOrDefault(merchantUuid, 0.0);
            if (!Objects.isNull(coupon) && coupon.getCreatorUuid().equals(UUID.fromString(merchantUuid)) && !coupon.isUsed())
                switch (coupon.getType()) {
                    case GIFT_CARD -> finalPrice += rawTotalPrice <= coupon.getValue() ? 0 : rawTotalPrice - coupon.getValue();
                    case PERCENTAGE_DISCOUNT -> finalPrice += rawTotalPrice * (100 - coupon.getValue()) / 100;
                    default -> finalPrice += rawTotalPrice;
                }
            else
                finalPrice += rawTotalPrice;
        }
        return finalPrice;
    }

    public double calculateRawDeliveryFee() {
        double finalPrice = 0;
        Set<String> merchantUuidSet = merchantUuidSet();
        Map<String, Double> totalPriceMap = merchantTotalPriceMap();
        for (String merchantUuid: merchantUuidSet)
            finalPrice += totalPriceMap.getOrDefault(merchantUuid, 0.0) < 1000 ? 50 : totalPriceMap.get(merchantUuid);
        return finalPrice;
    }

    public double calculateDeliveryFeeWithCoupon() {
        double finalPrice = 0;
        Set<String> merchantUuidSet = merchantUuidSet();
        Map<String, Double> totalPriceMap = merchantTotalPriceMap();
        for (String merchantUuid: merchantUuidSet)
            if (!(!Objects.isNull(coupon) && coupon.getCreatorUuid().equals(UUID.fromString(merchantUuid)) && !coupon.isUsed() && coupon.getType().equals(CouponType.FREE_SHIPPING)))
                finalPrice += totalPriceMap.getOrDefault(merchantUuid, 0.0) < 1000 ? 50 : totalPriceMap.get(merchantUuid) * 0.05;
        return finalPrice;
    }

    public CartResponseM1 toCartResponseM1(CartResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new CartResponseM1();

        response.setOrderUuid(uuid.toString());
        response.setCreator(AccountUtils.getAccountWithUuid(creatorUuid).toAccountResponseM1(null));
        response.setOrderType(type.toString());
        return response;
    }

    public CartResponseM2 toCartResponseM2(CartResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new CartResponseM2();

        response = (CartResponseM2) this.toCartResponseM1(response);

        List<CartItemResponseM2> cartItems = new ArrayList<>();
        for (OrderItem cartItem : this.getOrderItems())
            cartItems.add(cartItem.toCartItemResponseM2(null));

        response.setDiscount(Objects.isNull(coupon) ? null : this.isCouponActive());
        response.setCoupon(Objects.isNull(coupon) ? null : coupon.toCouponResponseM1());
        response.setTotalPrice(this.calculateTotalPriceWithCoupon());
        response.setDeliveryFee(this.calculateDeliveryFeeWithCoupon());
        response.setCartItems(cartItems);
        return response;
    }

    public PaidResponseM1 toPaidResponseM1(PaidResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new PaidResponseM1();

        response = (PaidResponseM1) this.toCartResponseM1(response);
        response.setCheckoutDate(checkoutDate.toString());
        response.setPayType(type.toString());
        DeliveryInformation information = new DeliveryInformation();
        information.setFirstname(this.recipientFirstname);
        information.setLastname(this.recipientLastname);
        information.setPhone(this.recipientPhone);
        information.setAddress(this.recipientAddress);
        response.setDeliveryInformation(information);
        return response;
    }

    public PaidResponseM2 toPaidResponseM2(PaidResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new PaidResponseM2();

        response = (PaidResponseM2) this.toPaidResponseM1(response);

        List<PaidItemResponseM2> paidItems = new ArrayList<>();
        for (OrderItem paidItem: this.getOrderItems())
            paidItems.add(paidItem.toPaidItemResponseM2(null));

        response.setDiscount(Objects.isNull(coupon) ? null : this.isCouponActive());
        response.setCoupon(Objects.isNull(coupon) ? null : coupon.toCouponResponseM1());
        response.setTotalPrice(this.calculateTotalPriceWithCoupon());
        response.setDeliveryFee(this.calculateDeliveryFeeWithCoupon());
        response.setOrderItems(paidItems);
        return response;
    }

}
