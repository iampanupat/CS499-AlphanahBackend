package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.response.MImageBaseResponse;
import com.alphanah.alphanahbackend.service.AmazonS3Service;
import com.alphanah.alphanahbackend.service.ImageService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageBusiness {

    @Autowired
    private ImageService service;

    @Autowired
    private AccountUtils accountUtils;

    @Autowired
    private AmazonS3Service amazonS3Service;

    public List<MImageBaseResponse> getAllProductImages(UUID productUuid) throws AlphanahBaseException {
        List<MImageBaseResponse> responses = new ArrayList<>();
        List<Image> images = service.getAll(productUuid);
        for (Image image : images)
            responses.add(image.toMImageBaseResponse());
        return responses;
    }

    public MImageBaseResponse createProductImage(String token, UUID productUuid, MultipartFile image) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), productUuid, amazonS3Service.saveFile(image)).toMImageBaseResponse();
    }

    public void deleteProductImage(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), productUuid, uuid);
    }

    public List<MImageBaseResponse> getAllReviewImages(UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        List<MImageBaseResponse> responses = new ArrayList<>();
        List<Image> images = service.getAll(productUuid, reviewUuid);
        for (Image image : images)
            responses.add(image.toMImageBaseResponse());
        return responses;
    }

    public MImageBaseResponse createReviewImage(String token, UUID productUuid, UUID reviewUuid, MultipartFile image) throws AlphanahBaseException {
        return service.create(accountUtils.getAccount(token).getUuid(), productUuid, reviewUuid, amazonS3Service.saveFile(image)).toMImageBaseResponse();
    }

    public void deleteReviewImage(String token, UUID productUuid, UUID reviewUuid, UUID uuid) throws AlphanahBaseException {
        service.delete(accountUtils.getAccount(token).getUuid(), productUuid, reviewUuid, uuid);
    }

}
