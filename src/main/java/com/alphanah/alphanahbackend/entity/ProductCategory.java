package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import com.alphanah.alphanahbackend.model.product_category.ProductCategoryResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "product_categories")
public class ProductCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_uuid", nullable = false, updatable = false)
    private Category category;

    public ProductCategoryResponseM1 toProductCategoryResponseM1(ProductCategoryResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductCategoryResponseM1();

        response.setUuid(this.getUuid());
        response.setProduct(this.getProduct().toProductResponseM1(null));
        response.setCategory(this.getCategory().toCategoryResponseM1(null));
        return response;
    }

    public CategoryResponseM1 toCategoryResponseM1(CategoryResponseM1 response) {
        if (response == null)
            response = new CategoryResponseM1();

        return this.getCategory().toCategoryResponseM1(response);
    }

    public ProductResponseM2 toProductResponseM2(ProductResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM2();

        return this.getProduct().toProductResponseM2(response);
    }

}
