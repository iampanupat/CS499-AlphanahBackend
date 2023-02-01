package com.alphanah.alphanahbackend.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Objects;

public class JWTUtils {

    public static String removeBearer(String token) {
        if (Objects.isNull(token))
            return null;
        if (token.startsWith("Bearer "))
            return token.substring(7);
        else
            return token;
    }

    public static boolean isValid(String token) {
        try {
            JWT.decode(token);
            return true;
        } catch (JWTDecodeException exception) {
            return false;
        }
    }

    public static boolean isExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().before(new Date());
        } catch (JWTDecodeException exception) {
            throw exception;
        }
    }

}
