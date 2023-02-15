package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity(name = "images")
public class Image {

    @Id
    @Column(name = "image_uuid", nullable = false, updatable = false)
    @GeneratedValue
    private UUID uuid;

    @Column(name = "image_path", nullable = false, updatable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_uuid", updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "review_uuid", updatable = false)
    private Review review;

    public ImageResponseM1 toImageResponseM1(ImageResponseM1 response) {
        if (response == null)
            response = new ImageResponseM1();

        response.setImageUUID(this.getUuid().toString());
        response.setPath(this.getPath());
        return response;
    }

}
