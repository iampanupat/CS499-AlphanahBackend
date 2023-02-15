package com.alphanah.alphanahbackend.utility;

import org.joda.time.DateTimeZone;

import java.time.ZoneId;

public interface Environment {

    String IMAGE_SERVER_URL = "https://images.alphanah.com";

    String ALPHANAH = "Alphanah";
    String AMAZON = "Amazon";
    String SPRING_FRAMEWORK = "Spring Framework";

    int AWS_COGNITO_VALUE_MAX_LENGTH = 2048;

    int FIRSTNAME_MAX_LENGTH = 50;
    int LASTNAME_MAX_LENGTH = 50;
    int ADDRESS_MAX_LENGTH = 2048;
    int PHONE_MAX_LENGTH = 19;

    DateTimeZone bangkokZone = DateTimeZone.forID("Asia/Bangkok");

}
