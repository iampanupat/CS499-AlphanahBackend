package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ImageException;
import com.alphanah.alphanahbackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    private final int IMAGE_PATH_MAX_LENGTH = 255;

    // GET All "Product Image" of Product UUID
    public List<Image> getAll(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.getAllWithNullProductUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.getNullProductObject();
        }

        return repository.findAllByProduct(product);
    }

    // GET All "Review Image" of Review UUID
    public List<Image> getAll(UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.getAllWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.getAllWithNullReviewUuid();

        Review review;
        try {
            review = reviewService.get(productUuid, reviewUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.getNullReviewObject();
        }

        return repository.findAllByReview(review);
    }

    // CREATE a "Product Image"
    public Image create(UUID creatorUuid, UUID productUuid, String path) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.createWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.createWithNullProductUuid();

        if (Objects.isNull(path))
            throw ImageException.createWithNullPath();

        if (path.isEmpty())
            throw ImageException.createWithEmptyPath();

        if (path.length() > IMAGE_PATH_MAX_LENGTH)
            throw ImageException.createWithMaxLengthPath();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.createWithNullProductObject();
        }

        if (!product.getCreatorUuid().equals(creatorUuid.toString()))
            throw ImageException.createNotOwned();

        Image entity = new Image();
        entity.setProduct(product);
        entity.setPath(path);
        return repository.save(entity);
    }

    // CREATE a "Review Image"
    public Image create(UUID creatorUuid, UUID productUuid, UUID reviewUuid, String path) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.createWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.createWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.createWithNullReviewUuid();

        if (Objects.isNull(path))
            throw ImageException.createWithNullPath();

        if (path.isEmpty())
            throw ImageException.createWithEmptyPath();

        if (path.length() > IMAGE_PATH_MAX_LENGTH)
            throw ImageException.createWithMaxLengthPath();

        Review review;
        try {
            review = reviewService.get(productUuid, reviewUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.createWithNullReviewObject();
        }

        if (!review.getCreatorUuid().equals(creatorUuid.toString()))
            throw ImageException.createNotOwned();

        Image entity = new Image();
        entity.setReview(review);
        entity.setPath(path);
        return repository.save(entity);
    }

    // DELETE a "Product Image"
    public void delete(UUID creatorUuid, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.deleteWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ImageException.deleteWithNullUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.deleteWithNullProductObject();
        }

        Image entity;
        try {
            entity = this.get(productUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.deleteNullObject();
        }

        if (!entity.getProduct().getCreatorUuid().equals(creatorUuid.toString()))
            throw ImageException.deleteNotOwned();

        repository.delete(entity);
    }

    // DELETE a "Review Image"
    public void delete(UUID creatorUuid, UUID productUuid, UUID reviewUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.deleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.deleteWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.deleteWithNullReviewUuid();

        if (Objects.isNull(uuid))
            throw ImageException.deleteWithNullUuid();

        Image entity;
        try {
            entity = this.get(productUuid, reviewUuid, uuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.deleteNullObject();
        }

        if (!entity.getReview().getCreatorUuid().equals(creatorUuid.toString()))
            throw ImageException.deleteNotOwned();

        repository.delete(entity);
    }

    // GET a Specific "Product Image" of Image UUID
    // Use for DELETE "Product Image"
    private Image get(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.getWithNullProductUuid();

        if (Objects.isNull(uuid))
            throw ImageException.getWithNullUuid();

        Product product;
        try {
            product = productService.get(productUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.getWithNullProductObject();
        }

        Optional<Image> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw ImageException.getNullObject();

        Image image = optional.get();
        if (!image.getProduct().getUuid().equals(product.getUuid()))
            throw ImageException.getWithInvalidProductUuid();

        return image;
    }

    // GET a Specific "Review Image" of Image UUID
    // Use for DELETE "Review Image"
    private Image get(UUID productUuid, UUID reviewUuid, UUID uuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.getWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.getWithNullReviewUuid();

        if (Objects.isNull(uuid))
            throw ImageException.getWithNullUuid();

        Review review;
        try {
            review = reviewService.get(productUuid, reviewUuid);
        } catch (AlphanahBaseException exception) {
            throw ImageException.getWithNullReviewObject();
        }

        Optional<Image> optional = repository.findById(uuid.toString());
        if (optional.isEmpty())
            throw ImageException.getNullObject();

        Image image = optional.get();
        if (!image.getReview().getUuid().equals(review.getUuid()))
            throw ImageException.getWithInvalidReviewUuid();

        return image;
    }

}