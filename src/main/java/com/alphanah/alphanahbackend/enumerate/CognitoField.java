package com.alphanah.alphanahbackend.enumerate;

public enum CognitoField {

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

    private CognitoField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
    public static CognitoField get(String fieldName) {
        switch (fieldName) {
            case "sub" -> {
                return CognitoField.UUID;
            }
            case "email" -> {
                return CognitoField.EMAIL;
            }
            case "custom:role" -> {
                return CognitoField.ROLE;
            }
            case "name" -> {
                return CognitoField.FIRSTNAME;
            }
            case "family_name" -> {
                return CognitoField.LASTNAME;
            }
            case "address" -> {
                return CognitoField.ADDRESS;
            }
            case "phone_number" -> {
                return CognitoField.PHONE;
            }
            case "picture" -> {
                return CognitoField.IMAGE;
            }
            case "custom:cart_uuid" -> {
                return CognitoField.CART_UUID;
            }
            default -> {
                return null;
            }
        }
    }

}
