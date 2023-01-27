package com.alphanah.alphanahbackend.utility;

public class PhoneUtil {

    public static String addThaiAreaCode(String phoneNumber) {
        if (phoneNumber.startsWith("+")) {
            return phoneNumber;
        } else {
            return "+66" + phoneNumber;
        }
    }

    public static String removeThaiAreaCode(String phoneNumber) {
        if (phoneNumber.startsWith("+66")) {
            return phoneNumber.substring(3);
        } else {
            return phoneNumber;
        }
    }

}
