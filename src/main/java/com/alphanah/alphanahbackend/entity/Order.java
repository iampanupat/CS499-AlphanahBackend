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

    @Column(name = "or_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @Column(name = "or_status", nullable = false)
    private String status;

    @Column(name = "or_pay_type")
    private String payType;

    @Column(name = "or_pay_amount")
    private Double payAmount;

    @Column(name = "or_recipient_firstname")
    private String recipientFirstname;

    @Column(name = "or_recipient_lastname")
    private String recipientLastname;

    @Column(name = "or_recipient_phone")
    private String recipientPhone;

    @Column(name = "or_recipient_address")
    private String recipientAddress;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

}
