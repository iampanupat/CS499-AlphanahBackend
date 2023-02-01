package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "product_categories")
public class ProductCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "p_uuid", nullable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cat_uuid", nullable = false, updatable = false)
    private Category category;

    public MProductCategoryFullResponse toMProductCategoryFullResponse() {
        MProductCategoryFullResponse response = new MProductCategoryFullResponse();
        response.setUuid(this.getUuid());
        response.setProduct(this.getProduct().toMProductBaseResponse());
        response.setCategory(this.getCategory().toMCategoryBaseResponse());
        return response;
    }

    public MCategoryBaseResponse toMCategoryResponse() {
        MCategoryBaseResponse response = new MCategoryBaseResponse();
        response.setUuid(this.getCategory().getUuid());
        response.setName(this.getCategory().getName());
        response.setCreatorUuid(this.getCategory().getCreatorUuid());
        return response;
    }

    public MProductWithoutCategoryResponse toMProductWithoutCategoryResponse() {
        MProductWithoutCategoryResponse response = new MProductWithoutCategoryResponse();
        response.setUuid(this.getProduct().getUuid());
        response.setName(this.getProduct().getName());
        response.setDescription(this.getProduct().getDescription());
        response.setCreatorUuid(this.getProduct().getCreatorUuid());

        List<MProductOptionBaseResponse> options = new ArrayList<>();
        List<ProductOption> productOptionList = this.getProduct().getOptions();
        for (ProductOption option : productOptionList)
            options.add(option.toMProductOptionBaseResponse());
        response.setOptions(options);

        List<MImageBaseResponse> images = new ArrayList<>();
        List<Image> imageList = this.getProduct().getImages();
        for (Image image : imageList)
            images.add(image.toMImageBaseResponse());
        response.setImages(images);

        List<MReviewWithImageResponse> reviews = new ArrayList<>();
        List<Review> reviewList = this.getProduct().getReviews();
        for (Review review : reviewList)
            reviews.add(review.toMReviewWithImageResponse());
        response.setReviews(reviews);

        return response;
    }

}
