package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import com.alphanah.alphanahbackend.model.product_category.ProductCategoryResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "product_categories")
public class ProductCategory {

    @Id
    @Column(name = "product_category_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_uuid", nullable = false, updatable = false)
    private Category category;

    public ProductCategoryResponseM1 toProductCategoryResponseM1(ProductCategoryResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductCategoryResponseM1();

        response.setProduct(this.getProduct().toProductResponseM1());
        response.setCategory(this.getCategory().toCategoryResponseM1(null));
        return response;
    }

}