package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.entity.ProductCategory;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductRequest;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.service.ProductService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductBusiness {

    @Autowired
    private ProductService service;

    public List<ProductResponseM3> getAllProducts(String name, String category, UUID merchantUuid) throws AlphanahBaseException {
        List<ProductResponseM3> responses = new ArrayList<>();
        List<Product> productList = service.findAllProducts();
        for (Product product: productList) {
            if (!Objects.isNull(name) && !product.getName().toLowerCase().contains(name.toLowerCase()))
                continue;

            boolean rightCategory = false;
            if (!Objects.isNull(category)) {
                for (ProductCategory productCategory: product.getProductCategories()) {
                    if (productCategory.getCategory().getName().equalsIgnoreCase(category)) {
                        rightCategory = true;
                        break;
                    }
                }
                if (!rightCategory)
                    continue;
            }

            if (!Objects.isNull(merchantUuid) && !product.getCreatorUuid().equals(merchantUuid))
                continue;
            responses.add(product.toProductResponseM3());
        }
        return responses;
    }

    public ProductResponseM3 getProduct(UUID uuid) throws AlphanahBaseException {
        Product product = service.findProduct(uuid);
        ProductResponseM3 response = product.toProductResponseM3();

        long productCount = 0L;
        long reviewCount = 0L;
        List<Product> productList = service.findAllProducts();
        for (Product prod: productList) {
            if (prod.getCreatorUuid().equals((product.getCreatorUuid()))) {
                productCount++;
                reviewCount += prod.getReviews().size();
            }
        }

        AccountResponseM1 merchantResponse = AccountUtils.findAccount(product.getCreatorUuid()).toAccountResponseM1();
        merchantResponse.setProductCount(productCount);
        merchantResponse.setReviewCount(reviewCount);
        response.setCreator(merchantResponse);
        return response;
    }

    public ProductResponseM1 createProduct(String token, ProductRequest request) throws AlphanahBaseException {
        Product response = service.createProduct(AccountUtils.findAccount(token).getUuid(), request.getName(), request.getDescription());
        return response.toProductResponseM1();
    }

    public ProductResponseM1 updateProduct(String token, UUID uuid, ProductRequest request) throws AlphanahBaseException {
        Product response = service.updateProduct(AccountUtils.findAccount(token).getUuid(), uuid, request.getName(), request.getDescription());
        return response.toProductResponseM1();
    }

    public void deleteProduct(String token, UUID uuid) throws AlphanahBaseException {
        service.deleteProduct(AccountUtils.findAccount(token).getUuid(), uuid);
    }

}