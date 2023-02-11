package com.alphanah.alphanahbackend.model.review;

import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
import lombok.Data;

import java.util.List;

@Data
public class ReviewResponseM2 extends ReviewResponseM1 {
    private List<ImageResponseM1> images;
}
