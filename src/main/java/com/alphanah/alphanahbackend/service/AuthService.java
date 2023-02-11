package com.alphanah.alphanahbackend.service;

import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.utility.AmazonUtils;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Value("${spring.security.oauth2.client.registration.cognito.userPoolId}")
    private String userPoolId;

    @Value("${spring.security.oauth2.client.registration.cognito.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.cognito.clientSecret}")
    private String clientSecret;

    public Map<String, String> createAccount(String email, String password, Role role) {
        AttributeType emailAttr = new AttributeType().withName("email").withValue(email);
        AttributeType emailVerifiedAttr = new AttributeType().withName("email_verified").withValue("true");

        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                .withUserPoolId(userPoolId)
                .withUsername(email)
                .withTemporaryPassword(password)
                .withUserAttributes(emailAttr, emailVerifiedAttr)
                .withMessageAction(MessageActionType.SUPPRESS)
                .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

        if (role == Role.MERCHANT) {
            createUserRequest.withUserAttributes(new AttributeType().withName("custom:role").withValue("merchant"));
        }

        cognitoClient.adminCreateUser(createUserRequest);

        AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                .withUsername(email)
                .withUserPoolId(userPoolId)
                .withPassword(password)
                .withPermanent(true);

        cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("role", Objects.equals(Role.MERCHANT, role) ? Role.MERCHANT.toString() : Role.CUSTOMER.toString());
        return response;
    }

    public AuthenticationResultType signInAccount(String email, String password) {
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("SECRET_HASH", AmazonUtils.calculateSecretHash(clientId, clientSecret, email));
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", password);

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(clientId)
                .withUserPoolId(userPoolId)
                .withAuthParameters(authParams);

        return cognitoClient.adminInitiateAuth(authRequest).getAuthenticationResult();
    }

}
