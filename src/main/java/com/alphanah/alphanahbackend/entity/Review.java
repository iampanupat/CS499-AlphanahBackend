package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM1;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM3;
import com.alphanah.alphanahbackend.model.review.ReviewResponseM2;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "reviews")
public class Review extends BaseEntity {

    @Column(name = "review_message", nullable = false)
    private String message;

    @Column(name = "review_rating", nullable = false)
    private Integer rating;

    @Column(name = "review_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @Column(name = "review_create_date", nullable = false, updatable = false, length = 29)
    private String createDate;

    @OneToMany(mappedBy = "review", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    public ReviewResponseM1 toReviewResponseM1(ReviewResponseM1 response) throws AlphanahBaseException {
        if (response == null)
            response = new ReviewResponseM1();

        response.setReviewUUID(this.getUuid());
        response.setMessage(this.getMessage());
        response.setRating(this.getRating().toString());
        response.setCreateDate(this.getCreateDate());
        response.setCreator(AccountUtils.findAccount(UUID.fromString(this.getCreatorUuid())).toAccountResponseM1());
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
        response.setProduct(this.getProduct().toProductResponseM1(null));
        return response;
    }

}
