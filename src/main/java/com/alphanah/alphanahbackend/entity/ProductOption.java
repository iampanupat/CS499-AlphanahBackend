package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.MProductOptionBaseResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "product_options")
public class ProductOption extends BaseEntity {

    @Column(name = "p_opt_name", nullable = false, length = 120)
    private String name;

    @Column(name = "p_opt_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "p_opt_price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "p_uuid", nullable = false)
    private Product rootProduct;

    @OneToMany(mappedBy = "productOption", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public MProductOptionBaseResponse toMProductOptionBaseResponse() {
        MProductOptionBaseResponse response = new MProductOptionBaseResponse();
        response.setUuid(this.getUuid());
        response.setName(this.getName());
        response.setPrice(this.getPrice().toString());
        response.setQuantity(this.getQuantity().toString());
        return response;
    }

}
