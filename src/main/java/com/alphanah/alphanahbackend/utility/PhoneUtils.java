package com.alphanah.alphanahbackend.utility;

import java.util.Objects;

public class PhoneUtils {

    public static String addThaiAreaCode(String phoneNumber) {
        if (Objects.isNull(phoneNumber))
            return null;
        if (phoneNumber.equals(""))
            return null;
        if (phoneNumber.startsWith("+"))
            return phoneNumber;
        else
            return "+66" + phoneNumber;
    }

    public static String removeThaiAreaCode(String phoneNumber) {
        if (phoneNumber.startsWith("+66")) {
            return phoneNumber.substring(3);
        } else {
            return phoneNumber;
        }
    }

}
