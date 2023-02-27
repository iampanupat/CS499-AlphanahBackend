package com.alphanah.alphanahbackend.api;

import com.alphanah.alphanahbackend.business.AuthBusiness;
import com.alphanah.alphanahbackend.enumerate.Role;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthAPI {

    @Autowired
    private AuthBusiness business;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request, @RequestParam Role role) throws AlphanahBaseException {
        RegisterResponse response = business.register(request, role);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws AlphanahBaseException {
        LoginResponse response = business.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
