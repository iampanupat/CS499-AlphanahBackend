package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "product_options")
public class ProductOption extends BaseEntity {

    @Column(name = "product_option_name", nullable = false, length = 120)
    private String name;

    @Column(name = "product_option_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_option_price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productOption", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    public ProductOptionResponseM1 toProductOptionResponseM1(ProductOptionResponseM1 response) {
        if (response == null)
            response = new ProductOptionResponseM1();

        response.setUuid(this.getUuid());
        response.setName(this.getName());
        response.setPrice(this.getPrice().toString());
        response.setQuantity(this.getQuantity().toString());
        return response;
    }

}
