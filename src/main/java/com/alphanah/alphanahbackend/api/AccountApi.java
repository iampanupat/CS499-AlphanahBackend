package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AccountBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.MUpdateAccountRequest;
import com.alphanah.alphanahbackend.model.response.MAccountFullResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountApi {

    @Autowired
    private AccountBusiness business;

    @GetMapping
    public ResponseEntity<MAccountFullResponse> getAccount(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        MAccountFullResponse response = business.getAccount(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MAccountFullResponse> getAccountByUuid(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        MAccountFullResponse response = business.getAccountByUuid(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MAccountFullResponse> updateAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MUpdateAccountRequest request) throws AlphanahBaseException {
        MAccountFullResponse response = business.updateAccount(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/image")
    public ResponseEntity<MAccountFullResponse> updateAccountImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image) throws AlphanahBaseException {
        MAccountFullResponse response = business.updateAccountPicture(token, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
