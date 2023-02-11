package com.alphanah.alphanahbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "order_items")
public class OrderItem extends BaseEntity {

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

}
