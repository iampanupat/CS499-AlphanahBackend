package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.Review;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ImageException;
import com.alphanah.alphanahbackend.repository.ImageRepository;
import com.alphanah.alphanahbackend.repository.ProductRepository;
import com.alphanah.alphanahbackend.utility.Env;
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

    @Autowired
    private ProductRepository productRepository;

    public List<Image> findAllProductImages(UUID productUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.cannotFindWithNullProductUuid();

        Product product = productService.findProduct(productUuid);
        return repository.findAllByProduct(product);
    }

    public List<Image> findAllReviewImages(UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.cannotFindWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.cannotFindWithNullReviewUuid();

        Review review = reviewService.findReview(productUuid, reviewUuid);
        return repository.findAllByReview(review);
    }

    private Image findProductImage(UUID productUuid, UUID imageUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.cannotFindWithNullProductUuid();

        if (Objects.isNull(imageUuid))
            throw ImageException.cannotFindWithNullImageUuid();

        Product product = productService.findProduct(productUuid);

        Optional<Image> optional = repository.findById(imageUuid);
        if (optional.isEmpty())
            throw ImageException.notFound();

        Image image = optional.get();
        if (!image.getProduct().getUuid().equals(product.getUuid()))
            throw ImageException.cannotFindWithInvalidProductUuid();

        return image;
    }

    private Image findReviewImage(UUID productUuid, UUID reviewUuid, UUID imageUuid) throws AlphanahBaseException {
        if (Objects.isNull(productUuid))
            throw ImageException.cannotFindWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.cannotFindWithNullReviewUuid();

        if (Objects.isNull(imageUuid))
            throw ImageException.cannotFindWithNullImageUuid();

        Review review = reviewService.findReview(productUuid, reviewUuid);

        Optional<Image> optional = repository.findById(imageUuid);
        if (optional.isEmpty())
            throw ImageException.notFound();

        Image image = optional.get();
        if (!image.getReview().getUuid().equals(review.getUuid()))
            throw ImageException.cannotFindWithInvalidReviewUuid();

        return image;
    }

    public Product createProductMainImage(UUID creatorUuid, UUID productUuid, String imagePath) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(imagePath))
            throw ImageException.cannotCreateWithNullImagePath();

        if (imagePath.isEmpty())
            throw ImageException.cannotCreateWithEmptyImagePath();

        if (imagePath.length() > Env.IMAGE_PATH_MAX_LENGTH)
            throw ImageException.cannotCreateWithImagePathExceedMaxLength();

        Product product = productService.findProduct(productUuid);
        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ImageException.cannotCreateNotOwned();

        Image image = new Image();
        image.setProduct(null);
        image.setPath(imagePath);
        repository.save(image);

        Product entity = productService.findProduct(productUuid);
        entity.setImage(image);
        return productRepository.save(entity);
    }

    public Image createProductImage(UUID creatorUuid, UUID productUuid, String imagePath) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(imagePath))
            throw ImageException.cannotCreateWithNullImagePath();

        if (imagePath.isEmpty())
            throw ImageException.cannotCreateWithEmptyImagePath();

        if (imagePath.length() > Env.IMAGE_PATH_MAX_LENGTH)
            throw ImageException.cannotCreateWithImagePathExceedMaxLength();

        Product product = productService.findProduct(productUuid);
        if (!product.getCreatorUuid().equals(creatorUuid))
            throw ImageException.cannotCreateNotOwned();

        Image entity = new Image();
        entity.setProduct(product);
        entity.setPath(imagePath);
        return repository.save(entity);
    }

    public Image createReviewImage(UUID creatorUuid, UUID productUuid, UUID reviewUuid, String imagePath) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.cannotCreateWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.cannotCreateWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.cannotCreateWithNullReviewUuid();

        if (Objects.isNull(imagePath))
            throw ImageException.cannotCreateWithNullImagePath();

        if (imagePath.isEmpty())
            throw ImageException.cannotCreateWithEmptyImagePath();

        if (imagePath.length() > Env.IMAGE_PATH_MAX_LENGTH)
            throw ImageException.cannotCreateWithImagePathExceedMaxLength();

        Review review = reviewService.findReview(productUuid, reviewUuid);
        if (!review.getCreatorUuid().equals(creatorUuid))
            throw ImageException.cannotCreateNotOwned();

        Image entity = new Image();
        entity.setReview(review);
        entity.setPath(imagePath);
        return repository.save(entity);
    }

    public void deleteProductImage(UUID creatorUuid, UUID productUuid, UUID imageUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(imageUuid))
            throw ImageException.cannotDeleteWithNullImageUuid();

        Image entity = this.findProductImage(productUuid, imageUuid);
        if (!entity.getProduct().getCreatorUuid().equals(creatorUuid))
            throw ImageException.cannotDeleteNotOwned();

        repository.delete(entity);
    }

    public void deleteReviewImage(UUID creatorUuid, UUID productUuid, UUID reviewUuid, UUID imageUuid) throws AlphanahBaseException {
        if (Objects.isNull(creatorUuid))
            throw ImageException.cannotDeleteWithNullCreatorUuid();

        if (Objects.isNull(productUuid))
            throw ImageException.cannotDeleteWithNullProductUuid();

        if (Objects.isNull(reviewUuid))
            throw ImageException.cannotDeleteWithNullReviewUuid();

        if (Objects.isNull(imageUuid))
            throw ImageException.cannotDeleteWithNullImageUuid();

        Image entity = this.findReviewImage(productUuid, reviewUuid, imageUuid);
        if (!entity.getReview().getCreatorUuid().equals(creatorUuid))
            throw ImageException.cannotDeleteNotOwned();

        repository.delete(entity);
    }

}