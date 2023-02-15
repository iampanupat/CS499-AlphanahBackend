package com.alphanah.alphanahbackend.entity;

import com.alphanah.alphanahbackend.model.account.AccountResponseM1;
import com.alphanah.alphanahbackend.enumerate.Role;
import com.alphanah.alphanahbackend.model.account.AccountResponseM2;
import com.alphanah.alphanahbackend.utility.Env;
import lombok.Data;

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
        this.image = "/defaultProfilePicture.jpg";
    }

    public boolean isCustomer() {
        return this.role == Role.CUSTOMER;
    }

    public boolean isMerchant() {
        return this.role == Role.MERCHANT;
    }

    public AccountResponseM1 toAccountResponseM1() {
        return this.toAccountResponseM1(null);
    }

    private AccountResponseM1 toAccountResponseM1(AccountResponseM1 response) {
        if (response == null)
            response = new AccountResponseM1();

        response.setAccountUUID(this.getUuid().toString());
        response.setRole(this.getRole().toString());
        response.setFirstname(this.getFirstname());
        response.setLastname(this.getLastname());
        response.setImage(Env.IMAGE_SERVER_URL + this.getImage());
        response.setCreateDate(this.getCreateDate());
        return response;
    }

    public AccountResponseM2 toAccountResponseM2() {
        return this.toAccountResponseM2(null);
    }

    private AccountResponseM2 toAccountResponseM2(AccountResponseM2 response) {
        if (response == null)
            response = new AccountResponseM2();

        response = (AccountResponseM2) this.toAccountResponseM1(response);
        response.setEmail(this.getEmail());
        response.setAddress(this.getAddress());
        response.setPhone(this.getPhone());
        response.setCartUUID(Objects.isNull(this.getCartUuid()) ? null : this.getCartUuid().toString());
        return response;
    }

}
