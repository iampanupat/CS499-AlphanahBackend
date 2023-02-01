package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.response.MAccountBaseResponse;
import com.alphanah.alphanahbackend.model.enumerate.ERole;
import com.alphanah.alphanahbackend.model.response.MAccountFullResponse;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Account {
    private UUID uuid;
    private String email;
    private ERole role;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String image;
    private UUID cartUuid;

    public Account() {
        this.role = ERole.CUSTOMER;
        this.image = "https://images.alphanah.com/defaultProfilePicture.jpg";
    }

    public boolean isCustomer() {
        return this.role == ERole.CUSTOMER;
    }

    public boolean isMerchant() {
        return this.role == ERole.MERCHANT;
    }

    public MAccountFullResponse toMAccountFullResponse() {
        MAccountFullResponse response = new MAccountFullResponse();
        response.setUuid(this.getUuid().toString());
        response.setRole(this.getRole().name());
        response.setFirstname(this.getFirstname());
        response.setLastname(this.getLastname());
        response.setImage(this.getImage());
        response.setEmail(this.getEmail());
        response.setAddress(this.getAddress());
        response.setPhone(this.getPhone());
        if (!Objects.isNull(this.getCartUuid()))
            response.setCartUuid(this.getCartUuid().toString());
        return response;
    }

}
