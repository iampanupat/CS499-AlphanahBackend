package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.account.MGetAccountResponse;
import com.alphanah.alphanahbackend.model.enumerate.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class Account {
    private UUID uuid;
    private String email;
    private Role role;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String picture;
    private UUID cartUuid;

    public Account() {
        this.role = Role.CUSTOMER;
        this.picture = "https://images.alphanah.com/defaultProfilePicture.jpg";
    }

    public boolean isCustomer() {
        return this.role == Role.CUSTOMER;
    }

    public boolean isMerchant() {
        return this.role == Role.MERCHANT;
    }

    public MGetAccountResponse toMGetAccountResponse() {
        MGetAccountResponse response = new MGetAccountResponse();
        response.setUuid(uuid);
        response.setEmail(email);
        response.setRole(role);
        response.setFirstname(firstname);
        response.setLastname(lastname);
        response.setAddress(address);
        response.setPhone(phone);
        response.setPicture(picture);
        response.setCartUuid(cartUuid);
        return response;
    }

}
