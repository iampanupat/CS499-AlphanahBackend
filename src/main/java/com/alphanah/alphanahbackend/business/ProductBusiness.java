package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.ProductException;
import com.alphanah.alphanahbackend.model.product.ProductRequest;
import com.alphanah.alphanahbackend.model.product.ProductResponseM1;
import com.alphanah.alphanahbackend.model.product.ProductResponseM3;
import com.alphanah.alphanahbackend.service.ProductService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductBusiness {

    @Autowired
    private ProductService service;

    public List<ProductResponseM3> getAllProducts(String name, String description, UUID merchantUuid) throws AlphanahBaseException {
        List<ProductResponseM3> responses = new ArrayList<>();
        List<Product> productList = service.findAllProducts();
        for (Product product: productList) {
            if (!Objects.isNull(name) && !product.getName().toLowerCase().contains(name.toLowerCase()))
                continue;
            if (!Objects.isNull(description) && !product.getDescription().toLowerCase().contains(description.toLowerCase()))
                continue;
            if (!Objects.isNull(merchantUuid) && !product.getCreatorUuid().equals(merchantUuid))
                continue;
            responses.add(product.toProductResponseM3());
        }
        return responses;
    }

    public ProductResponseM3 getProduct(UUID uuid) throws AlphanahBaseException {
        Product response = service.findProduct(uuid);
        return response.toProductResponseM3();
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