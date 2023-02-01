package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Product;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product.root.MCreateProductRequest;
import com.alphanah.alphanahbackend.model.response.MAccountFullResponse;
import com.alphanah.alphanahbackend.model.response.MProductBaseResponse;
import com.alphanah.alphanahbackend.model.response.MProductFullResponse;
import com.alphanah.alphanahbackend.model.product.root.MUpdateProductRequest;
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

    @Autowired
    private AccountUtils accountUtils;

    public List<MProductFullResponse> getAllProducts() {
        List<MProductFullResponse> responses = new ArrayList<>();
        List<Product> products = service.getAll();
        for (Product product : products)
            responses.add(product.toMProductFullResponse());
        return responses;
    }

    public MProductFullResponse getProduct(UUID uuid) throws AlphanahBaseException {
        return service.get(uuid).toMProductFullResponse();
    }

    public MProductBaseResponse createProduct(String token, MCreateProductRequest request) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), request.getName(), request.getDescription()).toMProductBaseResponse();
    }

    public MProductBaseResponse updateProduct(String token, UUID uuid, MUpdateProductRequest request) throws AlphanahBaseException {
        return service.update(accountUtils.getAccount(token).getUuid(), uuid, request.getName(), request.getDescription()).toMProductBaseResponse();
    }

    public void deleteProduct(String token, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), uuid);
    }

}
