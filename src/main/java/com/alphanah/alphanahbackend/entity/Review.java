package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM3;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.alphanah.alphanahbackend.utility.DateUtils;
import com.alphanah.alphanahbackend.utility.Env;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "reviews")
public class Review {

    @Id
    @Column(name = "review_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "review_message", nullable = false)
    private String message;

    @Column(name = "review_rating", nullable = false)
    private Integer rating;

    @Column(name = "review_creator_uuid", nullable = false, updatable = false)
    private UUID creatorUuid;

    @Column(name = "review_create_date", nullable = false, updatable = false)
    private Date createDate;

    @OneToMany(mappedBy = "review", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false, updatable = false)
    private Product product;

    public ReviewResponseM1 toReviewResponseM1(ReviewResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ReviewResponseM1();

        response.setReviewUUID(this.getUuid().toString());
        response.setMessage(this.getMessage());
        response.setRating(this.getRating().toString());
        response.setCreateDate(DateUtils.timeZoneConverter(this.getCreateDate(), Env.bangkokZone));
        response.setCreator(AccountUtils.findAccount(this.getCreatorUuid()).toAccountResponseM1());
        return response;
    }

    public ReviewResponseM2 toReviewResponseM2(ReviewResponseM2 response) throws AlphanahBaseException {
        if (response == null)
            response = new ReviewResponseM2();

        response = (ReviewResponseM2) this.toReviewResponseM1(response);
        List<ImageResponseM1> images = new ArrayList<>();
        List<Image> imageList = this.getImages();
        for (Image image : imageList)
            images.add(image.toImageResponseM1(null));
        response.setImages(images);
        return response;
    }

    public ReviewResponseM3 toReviewResponseM3(ReviewResponseM3 response) throws AlphanahBaseException {
        if (response == null)
            response = new ReviewResponseM3();

        response = (ReviewResponseM3) this.toReviewResponseM2(response);
        response.setProduct(this.getProduct().toProductResponseM1());
        return response;
    }

}
