package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "images")
public class Image extends BaseEntity {

    @Column(name = "image_path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "review_uuid")
    private Review review;

    public ImageResponseM1 toImageResponseM1(ImageResponseM1 response) {
        if (response == null)
            response = new ImageResponseM1();

        response.setImageUUID(this.getUuid());
        response.setPath(this.getPath());
        return response;
    }

}
