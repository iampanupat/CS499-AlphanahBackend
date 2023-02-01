package com.alphanah.alphanahbackend.model.enumerate;

public enum ECognitoField {

    UUID("sub"),
    EMAIL("email"),
    ROLE("custom:role"),
    FIRSTNAME("name"),
    LASTNAME("family_name"),
    ADDRESS("address"),
    PHONE("phone_number"),
    IMAGE("picture"),
    CART_UUID("custom:cart_uuid");

    private final String fieldName;

    private ECognitoField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
    public static ECognitoField get(String fieldName) {
        switch (fieldName) {
            case "sub" -> {
                return ECognitoField.UUID;
            }
            case "email" -> {
                return ECognitoField.EMAIL;
            }
            case "custom:role" -> {
                return ECognitoField.ROLE;
            }
            case "name" -> {
                return ECognitoField.FIRSTNAME;
            }
            case "family_name" -> {
                return ECognitoField.LASTNAME;
            }
            case "address" -> {
                return ECognitoField.ADDRESS;
            }
            case "phone_number" -> {
                return ECognitoField.PHONE;
            }
            case "picture" -> {
                return ECognitoField.IMAGE;
            }
            case "custom:cart_uuid" -> {
                return ECognitoField.CART_UUID;
            }
            default -> {
                return null;
            }
        }
    }

}
