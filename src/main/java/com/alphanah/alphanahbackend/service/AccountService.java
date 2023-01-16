package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.utility.TokenUtil;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    private static final String DEFAULT_PROFILE_PICTURE = "https://images.alphanah.com/defaultProfilePicture.jpg";

    public Map<String, String> getAccountAttributes(String bearerToken) {
        GetUserResult userResult = cognitoClient.getUser(new GetUserRequest().withAccessToken(TokenUtil.removeBearer(bearerToken)));
        Map<String, String> attributeMap = new HashMap<>();

        for (AttributeType attribute: userResult.getUserAttributes()) {
            if (Objects.equals(attribute.getName(), "sub")) {
                attributeMap.put("uuid", attribute.getValue());
            } else if (Objects.equals(attribute.getName(), "custom:role")) {
                attributeMap.put("role", attribute.getValue().toUpperCase());
            } else {
                attributeMap.put(attribute.getName(), attribute.getValue());
            }
        }

        if (Objects.isNull(attributeMap.get("role"))) {
            attributeMap.put("role", "CUSTOMER");
        }

        if (Objects.isNull(attributeMap.get("picture"))) {
            attributeMap.put("picture", DEFAULT_PROFILE_PICTURE);
        }

        return attributeMap;
    }

    public String getAccountAttribute(String bearerToken, String fieldName) {
        Map<String, String> attributeMap = this.getAccountAttributes(bearerToken);
        try {
            return attributeMap.get(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

}
