package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AuthBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.authentication.MLoginRequest;
import com.alphanah.alphanahbackend.model.authentication.MLoginResponse;
import com.alphanah.alphanahbackend.model.authentication.MRegisterRequest;
import com.alphanah.alphanahbackend.model.authentication.MRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApi {

    @Autowired
    private AuthBusiness business;

    @PostMapping("/register/customer")
    public ResponseEntity<MRegisterResponse> registerCustomer(@RequestBody MRegisterRequest request) throws AlphanahBaseException {
        MRegisterResponse response = business.customerRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @PostMapping("/register/merchant")
    public ResponseEntity<MRegisterResponse> registerMerchant(@RequestBody MRegisterRequest request) throws AlphanahBaseException {
        MRegisterResponse response = business.merchantRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<MLoginResponse> login(@RequestBody MLoginRequest request) throws AlphanahBaseException {
        MLoginResponse response = business.login(request);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
