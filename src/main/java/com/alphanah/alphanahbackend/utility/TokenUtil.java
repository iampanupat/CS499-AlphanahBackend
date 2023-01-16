package com.alphanah.alphanahbackend.utility;

public class TokenUtil {

    public static String removeBearer(String bearerToken) {
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return bearerToken;
        }
    }

}
