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
public class AccountAPI {

    @Autowired
    private AccountBusiness business;

    @GetMapping
    public ResponseEntity<AccountResponseM2> getAccountWithToken(@RequestHeader(value = "Authorization") String token) throws AlphanahBaseException {
        AccountResponseM2 response = business.getAccountWithToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{account_uuid}")
    public ResponseEntity<AccountResponseM1> getAccountWithUuid(@PathVariable("account_uuid") UUID accountUuid) throws AlphanahBaseException {
        AccountResponseM1 response = business.getAccountWithUuid(accountUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountResponseM2> updateAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody UpdateAccountRequest request
    ) throws AlphanahBaseException {
        AccountResponseM2 response = business.updateAccount(token, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/image")
    public ResponseEntity<AccountResponseM2> updateAccountImage(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody MultipartFile image) throws AlphanahBaseException {
        AccountResponseM2 response = business.updateAccountImage(token, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
