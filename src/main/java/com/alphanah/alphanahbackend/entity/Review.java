package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "reviews")
public class Review extends BaseEntity {

    @Column(name = "r_header", nullable = false, length = 120)
    private String header;

    @Column(name = "r_msg", nullable = false)
    private String message;

    @Column(name = "r_rating", nullable = false)
    private Integer rating;

    @Column(name = "r_creator_uuid", nullable = false, length = 36)
    private String creatorUuid;

    @OneToMany(mappedBy = "reviewImageOwner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "p_uuid", nullable = false)
    private Product productReviewOwner;

    private MReviewBaseResponse setMReviewBaseResponse(MReviewBaseResponse response) {
        response.setUuid(this.getUuid());
        response.setHeader(this.getHeader());
        response.setMessage(this.getMessage());
        response.setRating(this.getRating().toString());
        response.setCreatorUuid(this.getCreatorUuid());
        return response;
    }

    public MReviewBaseResponse toMReviewBaseResponse() {
        return this.setMReviewBaseResponse(new MReviewBaseResponse());
    }

    public MReviewWithImageResponse toMReviewWithImageResponse() {
        MReviewWithImageResponse response = (MReviewWithImageResponse) this.setMReviewBaseResponse(new MReviewWithImageResponse());
        List<MImageBaseResponse> images = new ArrayList<>();
        List<Image> imageList = this.getImages();
        for (Image image : imageList)
            images.add(image.toMImageBaseResponse());
        response.setImages(images);
        return response;
    }

    public MReviewFullResponse toMReviewFullResponse() {
        MReviewFullResponse response = (MReviewFullResponse) this.setMReviewBaseResponse(new MReviewFullResponse());
        List<MImageBaseResponse> images = new ArrayList<>();
        List<Image> imageList = this.getImages();
        for (Image image : imageList)
            images.add(image.toMImageBaseResponse());
        response.setImages(images);
        response.setProduct(this.getProductReviewOwner().toMProductBaseResponse());
        return response;
    }

}
