package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "product_options")
public class ProductOption {

    @Id
    @Column(name = "product_option_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "product_option_name", nullable = false, length = 120)
    private String name;

    @Column(name = "product_option_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_option_price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "productOption", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "product_soft_delete", nullable = false)
    private Boolean softDelete = false;

    public boolean isDeleted() {
        return softDelete;
    }

    public ProductOptionResponseM1 toProductOptionResponseM1(ProductOptionResponseM1 response) {
        if (response == null)
            response = new ProductOptionResponseM1();

        response.setOptionUUID(this.getUuid().toString());
        response.setName(this.getName());
        response.setPrice(this.getPrice().toString());
        response.setQuantity(this.getQuantity().toString());
        return response;
    }

}
