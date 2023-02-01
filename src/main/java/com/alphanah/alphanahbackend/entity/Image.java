package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.MImageBaseResponse;
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

    @Column(name = "img_path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "p_uuid")
    private Product productImageOwner;

    @ManyToOne
    @JoinColumn(name = "r_uuid")
    private Review reviewImageOwner;

    public MImageBaseResponse toMImageBaseResponse() {
        MImageBaseResponse response = new MImageBaseResponse();
        response.setUuid(this.getUuid());
        response.setPath(this.getPath());
        return response;
    }

}
