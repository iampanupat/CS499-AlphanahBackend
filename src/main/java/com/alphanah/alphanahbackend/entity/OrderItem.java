package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM1;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM1;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity(name = "order_items")
public class OrderItem implements Comparable<OrderItem> {

    @Id
    @Column(name = "order_item_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "order_uuid", nullable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_option_uuid", nullable = false, updatable = false)
    private ProductOption productOption;

    @Column(name = "order_item_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "order_item_price")
    private Double price;

    @Column(name = "order_item_delivery_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Override
    public int compareTo(OrderItem other) {
        Date thisDate = Objects.isNull(this.order.getCheckoutDate()) ? new Date() : this.order.getCheckoutDate();
        Date otherDate = Objects.isNull(other.order.getCheckoutDate()) ? new Date() : other.order.getCheckoutDate();
        return thisDate.compareTo(otherDate) * -1;
    }

    public CartItemResponseM1 toCartItemResponseM1(CartItemResponseM1 response) {
        if (response == null)
            response = new CartItemResponseM1();

        response.setOrderItemUUID(uuid.toString());
        response.setQuantity(this.getQuantity().toString());
        return response;
    }

    public CartItemResponseM2 toCartItemResponseM2(CartItemResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new CartItemResponseM2();

        response = (CartItemResponseM2) this.toCartItemResponseM1(response);
        response.setProduct(this.getProductOption().getProduct().toProductResponseM3());
        response.setOption(this.getProductOption().toProductOptionResponseM1(null));
        return response;
    }

    public PaidItemResponseM1 toPaidItemResponseM1(PaidItemResponseM1 response) {
        if (response == null)
            response = new PaidItemResponseM1();

        response = (PaidItemResponseM1) this.toCartItemResponseM1(response);
        response.setPrice(this.getPrice().toString());
        response.setDeliveryStatus(this.getDeliveryStatus().toString());
        return response;
    }

    public PaidItemResponseM2 toPaidItemResponseM2(PaidItemResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new PaidItemResponseM2();

        response = (PaidItemResponseM2) this.toPaidItemResponseM1(response);
        response.setProduct(this.getProductOption().getProduct().toProductResponseM3());
        response.setOption(this.getProductOption().toProductOptionResponseM1(null));
        response.setOrder(this.getOrder().toPaidResponseM1(null));
        return response;
    }

}
