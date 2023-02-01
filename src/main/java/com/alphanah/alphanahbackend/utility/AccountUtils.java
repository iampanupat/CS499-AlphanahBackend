package com.alphanah.alphanahbackend.utility;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.AuthException;
import com.alphanah.alphanahbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountUtils {

    @Autowired
    private AccountService accountService;

    public Account getAccount(String token) throws AlphanahBaseException {
        return accountService.get(token);
    }

    public void merchantVerify(String token) throws AlphanahBaseException {
        if (accountService.get(token).isMerchant())
            return;
        throw AuthException.roleNotAllowed();
    }

    public void customerVerify(String token) throws AlphanahBaseException {
        if (accountService.get(token).isCustomer())
            return;
        throw AuthException.roleNotAllowed();
    }

}
