package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.enumerate.DeliveryStatus;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.category.CategoryResponseM1;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM2;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.DateUtils;
import com.alphanah.alphanahbackend.utility.Env;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity(name = "products")
public class Product {

    @Id
    @Column(name = "product_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "product_name", nullable = false, length = 120)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_create_date", nullable = false, updatable = false)
    private Date createDate;

    @Column(name = "product_creator_uuid", nullable = false, updatable = false)
    private UUID creatorUuid;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductCategory> productCategories = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "product_main_image")
    private Image image;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public ProductResponseM1 toProductResponseM1() throws AlphanahBaseException {
        return this.toProductResponseM1(null);
    }

    private ProductResponseM1 toProductResponseM1(ProductResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM1();

        response.setProductUUID(uuid.toString());
        response.setName(name);
        response.setDescription(description);
        response.setCreator(AccountUtils.findAccount(creatorUuid).toAccountResponseM1());
        response.setCreateDate(DateUtils.timeZoneConverter(createDate, Env.bangkokZone));
        return response;
    }

    public ProductResponseM2 toProductResponseM2() throws AlphanahBaseException {
        return this.toProductResponseM2(null);
    }

    private ProductResponseM2 toProductResponseM2(ProductResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM2();

        response = (ProductResponseM2) this.toProductResponseM1(response);

        // List of "Product Option" Base Response
        List<ProductOptionResponseM1> options = new ArrayList<>();
        List<ProductOption> productOptionList = this.getOptions();
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (ProductOption option: productOptionList) {
            min = option.getPrice() < min ? option.getPrice() : min;
            max = option.getPrice() > max ? option.getPrice() : max;
            options.add(option.toProductOptionResponseM1(null));
        }
        response.setMinPrice(min == Double.MAX_VALUE ? 0 : min);
        response.setMaxPrice(max == Double.MIN_VALUE ? 0 : max);
        response.setOptions(options);

        response.setMainImage(Objects.isNull(image) ? null : image.toImageResponseM1(null));

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
        int totalCount = 0, oneStarCount = 0, twoStarCount = 0, threeStarCount = 0, fourStarCount = 0, fiveStarCount = 0;
        for (Review review : reviewList) {
            switch (review.getRating()) {
                case 1 -> oneStarCount++;
                case 2 -> twoStarCount++;
                case 3 -> threeStarCount++;
                case 4 -> fourStarCount++;
                case 5 -> fiveStarCount++;
            }
            sumReview += review.getRating();
            totalCount++;
            reviews.add(review.toReviewResponseM2(null));
        }
        response.setReviewScore(reviews.isEmpty() ? 0 : Math.round(sumReview / totalCount * 10.0) / 10.0);
        response.setReviewFiveStar(fiveStarCount);
        response.setReviewFourStar(fourStarCount);
        response.setReviewThreeStar(threeStarCount);
        response.setReviewTwoStar(twoStarCount);
        response.setReviewOneStar(oneStarCount);
        response.setReviews(reviews);
        return response;
    }

    public ProductResponseM3 toProductResponseM3() throws AlphanahBaseException {
        return this.toProductResponseM3(null);
    }

    private ProductResponseM3 toProductResponseM3(ProductResponseM3 response) throws AlphanahBaseException {
        if (response == null)
            response = new ProductResponseM3();

        response = (ProductResponseM3) this.toProductResponseM2(response);

        // List of "Category" Base Response
        List<CategoryResponseM1> categories = new ArrayList<>();
        List<ProductCategory> productCategoryList = this.getProductCategories();
        for (ProductCategory productCategory : productCategoryList)
            categories.add(productCategory.getCategory().toCategoryResponseM1(null));
        response.setCategories(categories);

        int saleCount = 0;
        int outOfStock = 0;
        List<ProductOption> productOptionList = this.options;
        for (ProductOption option : productOptionList) {
            if (option.getQuantity() == 0)
                outOfStock++;

            for (OrderItem orderItem : option.getOrderItems()) {
                if (orderItem.getDeliveryStatus().equals(DeliveryStatus.CART_ITEM))
                    continue;
                saleCount += orderItem.getQuantity();
            }
        }
        response.setSaleCount(saleCount);
        response.setOutOfStock(outOfStock);
        return response;
    }

}
