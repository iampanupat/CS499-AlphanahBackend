package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AuthBusiness;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.model.authentication.LoginRequest;
import com.alphanah.alphanahbackend.model.authentication.LoginResponse;
import com.alphanah.alphanahbackend.model.authentication.RegisterRequest;
import com.alphanah.alphanahbackend.model.authentication.RegisterResponse;
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
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest request) throws AlphanahBaseException {
        RegisterResponse response = business.customerRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @PostMapping("/register/merchant")
    public ResponseEntity<RegisterResponse> registerMerchant(@RequestBody RegisterRequest request) throws AlphanahBaseException {
        RegisterResponse response = business.merchantRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws AlphanahBaseException {
        LoginResponse response = business.login(request);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
