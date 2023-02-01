//package com.alphanah.alphanahbackend;
//
//import com.alphanah.alphanahbackend.entity.*;
//import com.alphanah.alphanahbackend.repository.ProductRepository;
//import com.alphanah.alphanahbackend.service.ProductService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class ProductServiceUnitTest {
//
//    @Autowired
//    ProductService service;
//
//    @Autowired
//    ProductRepository repository;
//
//    @Test
//    void testGetAll() {
//        List<Product> expectedList = (List<Product>) repository.findAll();
//        List<Product> actualList = service.getAll();
//        for (int i = 0; i < expectedList.size(); i++) {
//            Product expected = expectedList.get(i);
//            Product actual = actualList.get(i);
//
//            // Verify each product <- uuid
//            Assertions.assertEquals(expected.getUuid(), actual.getUuid());
//
//            // Verify each product <- name
//            Assertions.assertEquals(expected.getName(), actual.getName());
//
//            // Verify each product <- description
//            Assertions.assertEquals(expected.getDescription(), actual.getDescription());
//
//            // Verify each product <- creator uuid
//            Assertions.assertEquals(expected.getCreatorUuid(), actual.getCreatorUuid());
//
//            // Verify each product and category relationship
//            List<ProductCategory> expectedProductCategoryList = expected.getProductCategories();
//            List<ProductCategory> actualProductCategoryList = actual.getProductCategories();
//            for (int j = 0; j < expectedProductCategoryList.size(); j++) {
//                ProductCategory expectedProductCategory = expectedProductCategoryList.get(j);
//                ProductCategory actualProductCategory = actualProductCategoryList.get(j);
//
//                // Verify each product and category relationship <- uuid
//                Assertions.assertEquals(
//                        expectedProductCategory.getUuid(),
//                        actualProductCategory.getUuid()
//                );
//
//                // Verify each product and category relationship <- product uuid
//                Assertions.assertEquals(
//                        expectedProductCategory.getProduct().getUuid(),
//                        actualProductCategory.getProduct().getUuid()
//                );
//
//                // Verify each product and category relationship <- category uuid
//                Assertions.assertEquals(
//                        expectedProductCategory.getCategory().getUuid(),
//                        actualProductCategory.getCategory().getUuid()
//                );
//            }
//
//            // Verify each product and option relationship
//            List<ProductOption> expectedProductOptionList = expected.getOptions();
//            List<ProductOption> actualProductOptionList = actual.getOptions();
//            for (int j = 0; j < expectedProductOptionList.size(); j++) {
//                ProductOption expectedProductOption = expectedProductOptionList.get(j);
//                ProductOption actualProductOption = actualProductOptionList.get(j);
//
//                // Verify each product and option relationship <- uuid
//                Assertions.assertEquals(
//                        expectedProductOption.getUuid(),
//                        actualProductOption.getUuid()
//                );
//
//                // Verify each product and option relationship <- name
//                Assertions.assertEquals(
//                        expectedProductOption.getName(),
//                        actualProductOption.getName()
//                );
//
//                // Verify each product and option relationship <- quantity
//                Assertions.assertEquals(
//                        expectedProductOption.getQuantity(),
//                        actualProductOption.getQuantity()
//                );
//
//                // Verify each product and option relationship <- price
//                Assertions.assertEquals(
//                        expectedProductOption.getPrice(),
//                        actualProductOption.getPrice()
//                );
//
//                // Verify each product and option relationship <- product uuid
//                Assertions.assertEquals(
//                        expectedProductOption.getRootProduct().getUuid(),
//                        actualProductOption.getRootProduct().getUuid()
//                );
//            }
//
//            // Verify each product and image relationship
//            List<Image> expectedImageList = expected.getImages();
//            List<Image> actualImageList = actual.getImages();
//            for (int j = 0; j < expectedImageList.size(); j++) {
//                Image expectedImage = expectedImageList.get(j);
//                Image actualImage = actualImageList.get(j);
//
//                // Verify each product and image relationship <- uuid
//                Assertions.assertEquals(
//                        expectedImage.getUuid(),
//                        actualImage.getUuid()
//                );
//
//                // Verify each product and image relationship <- path
//                Assertions.assertEquals(
//                        expectedImage.getPath(),
//                        actualImage.getPath()
//                );
//
//                // Verify each product and image relationship <- product uuid
//                Assertions.assertEquals(
//                        expectedImage.getProductImageOwner().getUuid(),
//                        actualImage.getProductImageOwner().getUuid()
//                );
//
//                // Verify each product and image relationship <- review uuid
//                Assertions.assertEquals(
//                        expectedImage.getReviewImageOwner().getUuid(),
//                        actualImage.getReviewImageOwner().getUuid()
//                );
//            }
//
//            // Verify each product and review relationship
//            List<Review> expectedReviewList = expected.getReviews();
//            List<Review> actualReviewList = actual.getReviews();
//            for (int j = 0; j < expectedReviewList.size(); j++) {
//                Review expectedReview = expectedReviewList.get(j);
//                Review actualReview = actualReviewList.get(j);
//
//                // Verify each product and review relationship <- uuid
//                Assertions.assertEquals(
//                        expectedReview.getUuid(),
//                        actualReview.getUuid()
//                );
//
//                // Verify each product and review relationship <- header
//                Assertions.assertEquals(
//                        expectedReview.getHeader(),
//                        actualReview.getHeader()
//                );
//
//                // Verify each product and review relationship <- message
//                Assertions.assertEquals(
//                        expectedReview.getMessage(),
//                        actualReview.getMessage()
//                );
//
//                // Verify each product and review relationship <- rating
//                Assertions.assertEquals(
//                        expectedReview.getRating(),
//                        actualReview.getRating()
//                );
//
//                // Verify each product and review relationship <- creator uuid
//                Assertions.assertEquals(
//                        expectedReview.getCreatorUuid(),
//                        actualReview.getCreatorUuid()
//                );
//
//                // Verify each product and review relationship <- image relationship
//                List<Image> expectedReviewImageList = expectedReview.getImages();
//                List<Image> actualReviewImageList = actualReview.getImages();
//                for (int k = 0; k < expectedReviewImageList.size(); k++) {
//                    Image expectedReviewImage = expectedReviewImageList.get(k);
//                    Image actualReviewImage = actualReviewImageList.get(k);
//
//                    // Verify each product and review relationship <- image relationship <- uuid
//                    Assertions.assertEquals(
//                            expectedReviewImage.getUuid(),
//                            actualReviewImage.getUuid()
//                    );
//
//                    // Verify each product and review relationship <- image relationship <- path
//                    Assertions.assertEquals(
//                            expectedReviewImage.getPath(),
//                            actualReviewImage.getPath()
//                    );
//
//                    // Verify each product and review relationship <- image relationship <- product uuid
//                    Assertions.assertEquals(
//                            expectedReviewImage.getProductImageOwner().getUuid(),
//                            actualReviewImage.getProductImageOwner().getUuid()
//                    );
//
//                    // Verify each product and review relationship <- image relationship <- review uuid
//                    Assertions.assertEquals(
//                            expectedReviewImage.getReviewImageOwner().getUuid(),
//                            actualReviewImage.getReviewImageOwner().getUuid()
//                    );
//                }
//
//                // Verify each product and review relationship <- product uuid
//                Assertions.assertEquals(
//                        expectedReview.getProductReviewOwner().getUuid(),
//                        actualReview.getProductReviewOwner().getUuid()
//                );
//            }
//
//            // Verify each order and product relationship
//            List<OrderItem> expectedOrderItemList = expected.getOrderItems();
//            List<OrderItem> actualOrderItemList = actual.getOrderItems();
//            for (int j = 0; j < expectedOrderItemList.size(); j++) {
//                OrderItem expectedOrderItem = expectedOrderItemList.get(j);
//                OrderItem actualOrderItem = actualOrderItemList.get(j);
//
//                // Verify each order and product relationship <- uuid
//                Assertions.assertEquals(
//                        expectedOrderItem.getUuid(),
//                        actualOrderItem.getUuid()
//                );
//
//                // Verify each order and product relationship <- quantity
//                Assertions.assertEquals(
//                        expectedOrderItem.getQuantity(),
//                        actualOrderItem.getQuantity()
//                );
//
//                // Verify each order and product relationship <- price
//                Assertions.assertEquals(
//                        expectedOrderItem.getPrice(),
//                        actualOrderItem.getPrice()
//                );
//
//                // Verify each order and product relationship <- order uuid
//                Assertions.assertEquals(
//                        expectedOrderItem.getOrder().getUuid(),
//                        actualOrderItem.getOrder().getUuid()
//                );
//
//                // Verify each order and product relationship <- product uuid
//                Assertions.assertEquals(
//                        expectedOrderItem.getProduct().getUuid(),
//                        actualOrderItem.getProduct().getUuid()
//                );
//            }
//        }
//    }
//
//}
