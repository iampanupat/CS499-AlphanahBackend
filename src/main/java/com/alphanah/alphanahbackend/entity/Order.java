package com.alphanah.alphanahbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "order_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @Column(name = "order_type", nullable = false)
    private String type;

    @Column(name = "order_pay_type")
    private String payType;

    @Column(name = "order_pay_amount")
    private Double payAmount;

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

}
