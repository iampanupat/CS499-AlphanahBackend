package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AccountBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.model.account.MGetAccountResponse;
import com.alphanah.alphanahbackend.model.account.MUpdateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountApi {

    @Autowired
    private AccountBusiness business;

    // Get raw attributes from AWS Cognito for debug and testing
    // Please delete this function soon as possible
    @GetMapping("/rawAttributes")
    public ResponseEntity<Map<String, Object>> getAccountRawAttributes(@RequestHeader(value = "Authorization") String bearerToken) {
        Map<String, Object> response = business.getAccountRawAttributes(bearerToken);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @GetMapping
    public ResponseEntity<MGetAccountResponse> getAccount(@RequestHeader(value = "Authorization") String bearerToken) {
        MGetAccountResponse response = business.getAccount(bearerToken);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @RequestBody MUpdateAccountRequest request) throws AlphanahBaseException {
        business.updateAccount(bearerToken, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/picture")
    public ResponseEntity<Void> updateAccountPicture(
            @RequestHeader(value = "Authorization") String bearerToken,
            @RequestBody MultipartFile picture) throws AlphanahBaseException {
        business.updateAccountPicture(bearerToken, picture);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
