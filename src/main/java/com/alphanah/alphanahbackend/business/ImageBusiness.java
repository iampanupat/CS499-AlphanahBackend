package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Image;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.image.ImageResponseM1;
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
    private AmazonS3Service amazonS3Service;

    public List<ImageResponseM1> getAllProductImages(UUID productUuid) throws AlphanahBaseException {
        List<ImageResponseM1> responses = new ArrayList<>();
        List<Image> images = service.findAllProductImages(productUuid);
        for (Image image : images)
            responses.add(image.toImageResponseM1(null));
        return responses;
    }

    public ImageResponseM1 createProductImage(String token, UUID productUuid, MultipartFile image) throws AlphanahBaseException {
        Image response = service.createProductImage(AccountUtils.findAccount(token).getUuid(), productUuid, amazonS3Service.saveFile(image));
        return response.toImageResponseM1(null);
    }

    public void deleteProductImage(String token, UUID productUuid, UUID uuid) throws AlphanahBaseException {
        service.deleteProductImage(AccountUtils.findAccount(token).getUuid(), productUuid, uuid);
    }

    public List<ImageResponseM1> getAllReviewImages(UUID productUuid, UUID reviewUuid) throws AlphanahBaseException {
        List<ImageResponseM1> responses = new ArrayList<>();
        List<Image> images = service.findAllReviewImages(productUuid, reviewUuid);
        for (Image image : images)
            responses.add(image.toImageResponseM1(null));
        return responses;
    }

    public ImageResponseM1 createReviewImage(String token, UUID productUuid, UUID reviewUuid, MultipartFile image) throws AlphanahBaseException {
        Image response = service.createReviewImage(AccountUtils.findAccount(token).getUuid(), productUuid, reviewUuid, amazonS3Service.saveFile(image));
        return response.toImageResponseM1(null);
    }

    public void deleteReviewImage(String token, UUID productUuid, UUID reviewUuid, UUID uuid) throws AlphanahBaseException {
        service.deleteReviewImage(AccountUtils.findAccount(token).getUuid(), productUuid, reviewUuid, uuid);
    }

}