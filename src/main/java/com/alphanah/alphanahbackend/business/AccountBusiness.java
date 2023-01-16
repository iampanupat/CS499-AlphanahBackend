package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.service.AccountService;
import com.alphanah.alphanahbackend.utility.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountBusiness {

    @Autowired
    AccountService service;

    public Map<String, String> getAccountAttributes(String bearerToken) {
        return service.getAccountAttributes(bearerToken);
    }

    public Map<String, String> getAccountAttribute(String bearerToken, String fieldName) {
        Map<String, String> response = new HashMap<>();
        response.put(fieldName, service.getAccountAttribute(bearerToken, fieldName));
        return response;
    }

    // Work in progress
    public Map<String, String> updateAccountProfilePicture(String bearerToken, MultipartFile file) throws AlphanahBaseException {
        PictureUtil.validateFile(file);
        Map<String, String> response = new HashMap<>();
        return response;
    }

}
