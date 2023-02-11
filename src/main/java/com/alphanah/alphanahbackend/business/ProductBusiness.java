package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product.ProductRequest;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.service.ProductService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductBusiness {

    @Autowired
    private ProductService service;

    public List<ProductResponseM3> getAllProducts() throws AlphanahBaseException {
        List<ProductResponseM3> responses = new ArrayList<>();
        List<Product> products = service.getAll();
        for (Product product : products)
            responses.add(product.toProductResponseM3(null));
        return responses;
    }

    public ProductResponseM3 getProduct(UUID uuid) throws AlphanahBaseException {
        Product response = service.get(uuid);
        return response.toProductResponseM3(null);
    }

    public ProductResponseM1 createProduct(String token, ProductRequest request) throws AlphanahBaseException {
        Product response = service.create(AccountUtils.getAccountWithToken(token).getUuid(), request.getName(), request.getDescription());
        return response.toProductResponseM1(null);
    }

    public ProductResponseM1 updateProduct(String token, UUID uuid, ProductRequest request) throws AlphanahBaseException {
        Product response = service.update(AccountUtils.getAccountWithToken(token).getUuid(), uuid, request.getName(), request.getDescription());
        return response.toProductResponseM1(null);
    }

    public void deleteProduct(String token, UUID uuid) throws AlphanahBaseException {
        service.delete(AccountUtils.getAccountWithToken(token).getUuid(), uuid);
    }

}
