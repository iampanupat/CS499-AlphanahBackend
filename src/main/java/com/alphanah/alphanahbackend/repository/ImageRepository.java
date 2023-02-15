package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends CrudRepository<Image, UUID> {

    List<Image> findAllByProduct(Product product);

    List<Image> findAllByReview(Review review);

}
