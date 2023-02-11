package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AccountBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.account.UpdateAccountRequest;
import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
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
    public ResponseEntity<AccountResponseM2> getAccount(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        AccountResponseM2 response = business.getAccount(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AccountResponseM1> getAccountByUuid(@PathVariable("uuid") UUID uuid) throws AlphanahBaseException {
        AccountResponseM1 response = business.getAccountByUuid(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountResponseM2> updateAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody UpdateAccountRequest request) throws AlphanahBaseException {
        AccountResponseM2 response = business.updateAccount(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/image")
    public ResponseEntity<AccountResponseM2> updateAccountImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image) throws AlphanahBaseException {
        AccountResponseM2 response = business.updateAccountPicture(token, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
