package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "products")
public class Product extends BaseEntity {

    @Column(name = "p_name", nullable = false, length = 120)
    private String name;

    @Column(name = "p_description")
    private String description;

    @Column(name = "p_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @OneToMany(mappedBy = "rootProduct", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductOption> options;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductCategory> productCategories;

    @OneToMany(mappedBy = "productImageOwner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "productReviewOwner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews;

    private MProductBaseResponse setMProductBaseResponse(MProductBaseResponse response) {
        response.setUuid(this.getUuid());
        response.setName(this.getName());
        response.setDescription(this.getDescription());
        response.setCreatorUuid(this.getCreatorUuid());
        return response;
    }

    public MProductBaseResponse toMProductBaseResponse() {
        return this.setMProductBaseResponse(new MProductBaseResponse());
    }

    public MProductFullResponse toMProductFullResponse() {
        MProductFullResponse response = (MProductFullResponse) this.setMProductBaseResponse(new MProductFullResponse());

        List<MProductOptionBaseResponse> options = new ArrayList<>();
        List<ProductOption> productOptionList = this.getOptions();
        for (ProductOption productOption : productOptionList)
            options.add(productOption.toMProductOptionBaseResponse());
        response.setOptions(options);

        List<MCategoryBaseResponse> categories = new ArrayList<>();
        List<ProductCategory> productCategoryList = this.getProductCategories();
        for (ProductCategory productCategory : productCategoryList)
            categories.add(productCategory.toMCategoryResponse());
        response.setCategories(categories);

        List<MImageBaseResponse> images = new ArrayList<>();
        List<Image> imageList = this.getImages();
        for (Image image : imageList)
            images.add(image.toMImageBaseResponse());
        response.setImages(images);

        List<MReviewWithImageResponse> reviews = new ArrayList<>();
        List<Review> reviewList = this.getReviews();
        for (Review review : reviewList)
            reviews.add(review.toMReviewWithImageResponse());
        response.setReviews(reviews);

        return response;
    }

}
