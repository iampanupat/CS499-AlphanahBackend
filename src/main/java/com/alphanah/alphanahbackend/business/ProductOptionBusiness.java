package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionRequest;
import com.alphanah.alphanahbackend.model.product_option.ProductOptionResponseM1;
import com.alphanah.alphanahbackend.service.ProductOptionService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductOptionBusiness {

    @Autowired
    private ProductOptionService service;

    public List<ProductOptionResponseM1> getAllProductOptions(UUID productUuid) throws AlphanahBaseException {
        List<ProductOptionResponseM1> responses = new ArrayList<>();
        List<ProductOption> options = service.findAllProductOptions(productUuid);
        for (ProductOption option : options)
            responses.add(option.toProductOptionResponseM1(null));
        return responses;
    }

    public ProductOptionResponseM1 getProductOptions(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        ProductOption response = service.findProductOption(productUuid, uuid);
        return response.toProductOptionResponseM1(null);
    }

    public ProductOptionResponseM1 createProductOption(String token, UUID productUuid, ProductOptionRequest request) throws AlphanahBaseException {
        ProductOption response = service.createProductOption(AccountUtils.findAccount(token).getUuid(), productUuid, request.getName(), request.getPrice(), request.getQuantity());
        return response.toProductOptionResponseM1(null);
    }

    public ProductOptionResponseM1 updateProductOption(String token, UUID productUuid, UUID uuid, ProductOptionRequest request) throws AlphanahBaseException {
        ProductOption response = service.updateProductOption(AccountUtils.findAccount(token).getUuid(), productUuid, uuid, request.getName(), request.getPrice(), request.getQuantity());
        return response.toProductOptionResponseM1(null);
    }

    public void deleteProductOption(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.deleteProductOption(AccountUtils.findAccount(token).getUuid(), productUuid, uuid);
    }

}
