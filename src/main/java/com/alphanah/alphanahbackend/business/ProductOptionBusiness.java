package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.ProductOption;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.product.option.MCreateProductOptionRequest;
import com.alphanah.alphanahbackend.model.product.option.MUpdateProductOptionRequest;
import com.alphanah.alphanahbackend.model.response.MProductOptionBaseResponse;
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

    @Autowired
    private AccountUtils accountUtils;

    public List<MProductOptionBaseResponse> getAllProductOptions(UUID productUuid) throws AlphanahBaseException {
        List<MProductOptionBaseResponse> responses = new ArrayList<>();
        List<ProductOption> options = service.getAll(productUuid);
        for (ProductOption option : options)
            responses.add(option.toMProductOptionBaseResponse());
        return responses;
    }

    public MProductOptionBaseResponse getProductOptions(UUID productUuid, UUID uuid) throws AlphanahBaseException {
        return service.get(productUuid, uuid).toMProductOptionBaseResponse();
    }

    public MProductOptionBaseResponse createProductOption(String token, UUID productUuid, MCreateProductOptionRequest request) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), productUuid, request.getName(), request.getPrice(), request.getQuantity()).toMProductOptionBaseResponse();
    }

    public MProductOptionBaseResponse updateProductOption(String token, UUID productUuid, UUID uuid, MUpdateProductOptionRequest request) throws AlphanahBaseException {
        return service.update(accountUtils.getAccount(token).getUuid(), productUuid, uuid, request.getName(), request.getPrice(), request.getQuantity()).toMProductOptionBaseResponse();
    }

    public void deleteProductOption(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), productUuid, uuid);
    }

}
