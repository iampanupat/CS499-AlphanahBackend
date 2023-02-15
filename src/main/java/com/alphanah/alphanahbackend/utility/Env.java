package com.alphanah.alphanahbackend.utility;

import org.joda.time.DateTimeZone;

public interface Env {

    String IMAGE_SERVER_URL = "https://images.alphanah.com";

    DateTimeZone bangkokZone = DateTimeZone.forID("Asia/Bangkok");

    String ALPHANAH = "Alphanah";
    String AMAZON = "Amazon";
    String SPRING_FRAMEWORK = "Spring Framework";

    int AWS_COGNITO_VALUE_MAX_LENGTH = 2048;

    int EMAIL_MAX_LENGTH = 128;
    int PASSWORD_MAX_LENGTH = 256;

    int FIRSTNAME_MAX_LENGTH = 50;
    int LASTNAME_MAX_LENGTH = 50;
    int ADDRESS_MAX_LENGTH = 2048;
    int PHONE_MAX_LENGTH = 19;

    int PRODUCT_NAME_MAX_LENGTH = 120;
    int PRODUCT_DESCRIPTION_MAX_LENGTH = 255;
    int PRODUCT_PRICE_MAX_VALUE = 1000000;
    int PRODUCT_QUANTITY_MAX_VALUE = 1000000;

    int PRODUCT_OPTION_NAME_MAX_LENGTH = 120;

    int CATEGORY_NAME_MAX_LENGTH = 120;

    int REVIEW_MESSAGE_MAX_LENGTH = 255;
    int REVIEW_RATING_MIN_VALUE = 1;
    int REVIEW_RATING_MAX_VALUE = 5;

    int IMAGE_PATH_MAX_LENGTH = 255;

    int COUPON_PERCENTAGE_DISCOUNT_MAX_VALUE = 100;
    int COUPON_GIFT_CARD_MAX_VALUE = 1000000;

}
