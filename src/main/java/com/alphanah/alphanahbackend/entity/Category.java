package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM2;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM3;
import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "categories")
public class Category {

    @Id
    @Column(name = "category_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "category_name", nullable = false, updatable = false, length = 120)
    private String name;

    @Column(name = "category_level", nullable = false, updatable = false)
    private Integer level = 0;

    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductCategory> productCategories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_category_uuid")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Category> childCategories = new ArrayList<>();

    @JsonIgnore
    public List<Category> getAllChild() {
        return getAllChild(new ArrayList<>(), this);
    }

    private List<Category> getAllChild(List<Category> allChildList, Category root) {
        for (Category child : root.getChildCategories()) {
            allChildList.add(child);
            allChildList = getAllChild(allChildList, child);
        }
        return allChildList;
    }

    @JsonIgnore
    public List<Category> getAllParent() {
        return getAllParent(new ArrayList<>(), this);
    }
    private List<Category> getAllParent(List<Category> allParentList, Category root) {
        Category current = root;
        while (current.getParentCategory() != null) {
            allParentList.add(root.parentCategory);
            current = current.parentCategory;
        }
        return allParentList;
    }

    public CategoryResponseM1 toCategoryResponseM1(CategoryResponseM1 response) {
        if (response == null)
            response = new CategoryResponseM1();

        response.setCategoryUUID(this.getUuid().toString());
        response.setName(this.getName());
        response.setLevel(this.getLevel());
        return response;
    }

    public CategoryResponseM2 toCategoryResponseM2(CategoryResponseM2 response) {
        if (response == null)
            response = new CategoryResponseM2();

        response = (CategoryResponseM2) this.toCategoryResponseM1(response);
        if (this.getParentCategory() != null)
            response.setParentCategory(this.getParentCategory().toCategoryResponseM1(null));

        List<CategoryResponseM1> subcategories = new ArrayList<>();
        for (Category subCategory: this.getChildCategories())
            subcategories.add(subCategory.toCategoryResponseM1(null));
        response.setChildCategories(subcategories);
        return response;
    }

    public CategoryResponseM3 toCategoryResponseM3(CategoryResponseM3 response) throws AlphanahBaseException {
        if (response == null)
            response = new CategoryResponseM3();

        response = (CategoryResponseM3) this.toCategoryResponseM2(response);

        // List of (All Own "Product") and (All Subcategory "Product") (without Category) Response
        List<ProductCategory> productCategoryList = this.getProductCategories();
        for (Category subCategory: this.getAllChild()) {
            productCategoryList.addAll(subCategory.getProductCategories());
        }

        List<ProductResponseM2> products = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductResponseM2 productResponse = productCategory.getProduct().toProductResponseM2();
            if (products.contains(productResponse))
                continue;
            products.add(productResponse);
        }

        response.setProducts(products);
        return response;
    }

}
