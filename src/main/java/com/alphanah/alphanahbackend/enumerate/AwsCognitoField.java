package com.alphanah.alphanahbackend.enumerate;

public enum AwsCognitoField {

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

    private AwsCognitoField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
    public static AwsCognitoField get(String fieldName) {
        switch (fieldName) {
            case "sub" -> {
                return AwsCognitoField.UUID;
            }
            case "email" -> {
                return AwsCognitoField.EMAIL;
            }
            case "custom:role" -> {
                return AwsCognitoField.ROLE;
            }
            case "name" -> {
                return AwsCognitoField.FIRSTNAME;
            }
            case "family_name" -> {
                return AwsCognitoField.LASTNAME;
            }
            case "address" -> {
                return AwsCognitoField.ADDRESS;
            }
            case "phone_number" -> {
                return AwsCognitoField.PHONE;
            }
            case "picture" -> {
                return AwsCognitoField.IMAGE;
            }
            case "custom:cart_uuid" -> {
                return AwsCognitoField.CART_UUID;
            }
            default -> {
                return null;
            }
        }
    }

}
