package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "products")
public class Product extends BaseEntity {

    @Column(name = "product_name", nullable = false, length = 120)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public ProductResponseM1 toProductResponseM1(ProductResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM1();

        response.setProductUUID(this.getUuid());
        response.setName(this.getName());
        response.setDescription(this.getDescription());
        response.setCreator(AccountUtils.findAccount(UUID.fromString(this.getCreatorUuid())).toAccountResponseM1());
        return response;
    }

    public ProductResponseM2 toProductResponseM2(ProductResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM2();

        response = (ProductResponseM2) this.toProductResponseM1(response);

        // List of "Product Option" Base Response
        List<ProductOptionResponseM1> options = new ArrayList<>();
        List<ProductOption> productOptionList = this.getOptions();
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (ProductOption option : productOptionList) {
            min = option.getPrice() < min ? option.getPrice() : min;
            max = option.getPrice() > max ? option.getPrice() : max;
            options.add(option.toProductOptionResponseM1(null));
        }
        response.setMinPrice(min);
        response.setMaxPrice(max);
        response.setOptions(options);

        // "Image" Base Response
        List<ImageResponseM1> images = new ArrayList<>();
        List<Image> imageList = this.getImages();

        for (Image image : imageList)
            images.add(image.toImageResponseM1(null));
        response.setImages(images);

        // "Review" with Image Response
        List<ReviewResponseM2> reviews = new ArrayList<>();
        List<Review> reviewList = this.getReviews();
        double sumReview = 0;
        int count = 0;
        for (Review review : reviewList) {
            sumReview += review.getRating();
            count++;
            reviews.add(review.toReviewResponseM2(null));
        }
        response.setReviewScore(reviews.isEmpty() ? null : Math.round(sumReview / count * 10.0) / 10.0);
        response.setReviews(reviews);

        return response;
    }

    public ProductResponseM3 toProductResponseM3(ProductResponseM3 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM3();

        response = (ProductResponseM3) this.toProductResponseM2(response);

        List<CategoryResponseM1> categories = new ArrayList<>();
        List<ProductCategory> productCategoryList = this.getProductCategories();
        for (ProductCategory productCategory : productCategoryList)
            categories.add(productCategory.toCategoryResponseM1(null));
        response.setCategories(categories);

        return response;
    }

}
