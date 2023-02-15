package com.alphanah.alphanahbackend.repository;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {

    List<Review> findAllByProduct(Product product);

}
