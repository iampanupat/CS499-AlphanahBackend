package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
import lombok.Data;

import java.util.Date;
import java.util.Objects;
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
    private String image;
    private UUID cartUuid;
    private String createDate;

    public Account() {
        this.role = Role.CUSTOMER;
        this.image = "https://images.alphanah.com/defaultProfilePicture.jpg";
    }

    public boolean isCustomer() {
        return this.role == Role.CUSTOMER;
    }

    public boolean isMerchant() {
        return this.role == Role.MERCHANT;
    }

    public AccountResponseM1 toAccountResponseM1(AccountResponseM1 response) {
        if (response == null)
            response = new AccountResponseM1();

        response.setUuid(this.getUuid().toString());
        response.setRole(this.getRole().toString());
        response.setFirstname(this.getFirstname());
        response.setLastname(this.getLastname());
        response.setImage(this.getImage());
        response.setCreateDate(this.getCreateDate());
        return response;
    }

    public AccountResponseM2 toAccountResponseM2(AccountResponseM2 response) {
        if (response == null)
            response = new AccountResponseM2();

        response = (AccountResponseM2) this.toAccountResponseM1(response);
        response.setEmail(this.getEmail());
        response.setAddress(this.getAddress());
        response.setPhone(this.getPhone());
        if (!Objects.isNull(this.getCartUuid()))
            response.setCartUuid(this.getCartUuid().toString());
        return response;
    }

}
