package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM1;
import com.alphanah.alphanahbackend.model.order_item.CartItemResponseM2;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM1;
import com.alphanah.alphanahbackend.model.order_item.PaidItemResponseM2;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "order_items")
public class OrderItem extends BaseEntity implements Comparable<OrderItem> {

    @Column(name = "order_item_quantity")
    private Integer quantity;

    @Column(name = "order_item_price")
    private Double price;

    @Column(name = "order_item_delivery_status")
    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "order_uuid", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_option_uuid", nullable = false)
    private ProductOption productOption;

    @Override
    public int compareTo(OrderItem other) {
        DateTime thisCheckoutDate;
        try {
            thisCheckoutDate = DateTime.parse(this.getOrder().getCheckoutDate());
        } catch (Exception exception) {
            thisCheckoutDate = DateTime.now();
        }

        DateTime otherCheckoutDate;
        try {
            otherCheckoutDate = DateTime.parse(other.getOrder().getCheckoutDate());
        } catch (Exception exception) {
            otherCheckoutDate = DateTime.now();
        }

        return thisCheckoutDate.compareTo(otherCheckoutDate) * -1;
    }

    public CartItemResponseM1 toCartItemResponseM1(CartItemResponseM1 response) {
        if (response == null)
            response = new CartItemResponseM1();

        response.setOrderItemUuid(this.getUuid());
        response.setQuantity(this.getQuantity().toString());
        return response;
    }

    public CartItemResponseM2 toCartItemResponseM2(CartItemResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new CartItemResponseM2();

        response = (CartItemResponseM2) this.toCartItemResponseM1(response);
        response.setProduct(this.getProductOption().getProduct().toProductResponseM1(null));
        response.setOption(this.getProductOption().toProductOptionResponseM1(null));
        return response;
    }

    public PaidItemResponseM1 toPaidItemResponseM1(PaidItemResponseM1 response) {
        if (response == null)
            response = new PaidItemResponseM1();

        response = (PaidItemResponseM1) this.toCartItemResponseM1(response);
        response.setPrice(this.getPrice().toString());
        response.setDeliveryStatus(this.getDeliveryStatus());
        return response;
    }

    public PaidItemResponseM2 toPaidItemResponseM2(PaidItemResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new PaidItemResponseM2();

        response = (PaidItemResponseM2) this.toPaidItemResponseM1(response);
        response.setProduct(this.getProductOption().getProduct().toProductResponseM1(null));
        response.setOption(this.getProductOption().toProductOptionResponseM1(null));
        response.setOrder(this.getOrder().toPaidResponseM1(null));
        return response;
    }

}
