package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.model.enumerate.Role;
import com.alphanah.alphanahbackend.utility.PhoneUtil;
import com.alphanah.alphanahbackend.utility.TokenUtil;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Value("${spring.security.oauth2.client.registration.cognito.userPoolId}")
    private String userPoolId;

    // Get raw attributes from AWS Cognito for debug and testing
    // Please delete this function soon as possible
    public Map<String, Object> getAccountRawAttributes(String bearerToken) {
        GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(TokenUtil.removeBearer(bearerToken)));
        Map<String, Object> attributeMap = new HashMap<>();
        for (AttributeType attribute: userResult.getUserAttributes()) {
            attributeMap.put(attribute.getName(), attribute.getValue());
        }
        return attributeMap;
    }

    public Account getAccount(String bearerToken) {
        GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(TokenUtil.removeBearer(bearerToken)));
        Account account = new Account();

        // Convert AWS Cognito attributes name
        for (AttributeType attribute: userResult.getUserAttributes()) {
            String cognitoKey = attribute.getName();
            String cognitoValue = attribute.getValue();
            switch (cognitoKey) {
                case "sub" -> account.setUuid(UUID.fromString(cognitoValue));
                case "email" -> account.setEmail(cognitoValue);
                case "custom:role" -> account.setRole(Role.valueOf(cognitoValue.toUpperCase()));
                case "name" -> account.setFirstname(cognitoValue);
                case "family_name" -> account.setLastname(cognitoValue);
                case "address" -> account.setAddress(cognitoValue);
                case "phone_number" -> account.setPhone(PhoneUtil.removeThaiAreaCode(cognitoValue));
                case "picture" -> account.setPicture(cognitoValue);
                case "custom:cart_uuid" -> account.setCartUuid(UUID.fromString(cognitoValue));
            }
        }
        return account;
    }

    public void updateAccount(String bearerToken, String cognitoFieldName, String value) {
        GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(TokenUtil.removeBearer(bearerToken)));
        try {
            cognitoClient.adminUpdateUserAttributes(new AdminUpdateUserAttributesRequest()
                    .withUsername(userResult.getUsername())
                    .withUserPoolId(userPoolId)
                    .withUserAttributes(new AttributeType().withName(cognitoFieldName).withValue(value))
            );
        } catch (AmazonServiceException exception) {
            throw exception;
        }
    }

}
