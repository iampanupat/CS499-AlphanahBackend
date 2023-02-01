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

    @Column(name = "or_item_quantity")
    private Integer quantity;

    @Column(name = "or_item_price")
    private Double price;

    @Column(name = "or_item_delivery_status")
    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "or_uuid", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "p_opt_uuid", nullable = false)
    private ProductOption productOption;

}
