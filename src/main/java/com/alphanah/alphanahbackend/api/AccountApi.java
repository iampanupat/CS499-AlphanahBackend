package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AccountBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountApi {

    @Autowired
    AccountBusiness business;

    @GetMapping
    public ResponseEntity<Map<String, String>> getAccountAttributes(@RequestHeader(value = "Authorization") String bearerToken) {
        Map<String,String> response = business.getAccountAttributes(bearerToken);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @GetMapping("/{attribute}")
    public ResponseEntity<Map<String, String>> getAccountAttribute(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable String attribute) {
        Map<String, String> response = business.getAccountAttribute(bearerToken, attribute);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
