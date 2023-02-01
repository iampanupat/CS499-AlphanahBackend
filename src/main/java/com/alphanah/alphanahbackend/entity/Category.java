package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.MCategoryBaseResponse;
import com.alphanah.alphanahbackend.model.response.MCategoryFullResponse;
import com.alphanah.alphanahbackend.model.response.MProductWithoutCategoryResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "cat_name", nullable = false, length = 120, unique = true)
    private String name;

    @Column(name = "cat_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductCategory> productCategories;

    private MCategoryBaseResponse setMCategoryBaseResponse(MCategoryBaseResponse response) {
        response.setUuid(this.getUuid());
        response.setName(this.getName());
        response.setCreatorUuid(this.getCreatorUuid());
        return response;
    }

    public MCategoryBaseResponse toMCategoryBaseResponse() {
        return this.setMCategoryBaseResponse(new MCategoryBaseResponse());
    }

    public MCategoryFullResponse toMCategoryFullResponse() {
        MCategoryFullResponse response = (MCategoryFullResponse) this.setMCategoryBaseResponse(new MCategoryFullResponse());
        List<MProductWithoutCategoryResponse> products = new ArrayList<>();
        List<ProductCategory> productCategoryList = this.getProductCategories();
        for (ProductCategory productCategory : productCategoryList)
            products.add(productCategory.toMProductWithoutCategoryResponse());
        response.setProducts(products);
        return response;
    }

}
