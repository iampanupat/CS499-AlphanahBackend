package com.alphanah.alphanahbackend.utility;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.AuthException;
import com.alphanah.alphanahbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountUtils {

    private static AccountService accountService;

    @Autowired
    public AccountUtils(AccountService accountService) {
        AccountUtils.accountService = accountService;
    }

    public static Account getAccountWithUuid(UUID uuid) throws AlphanahBaseException {
        return accountService.get(uuid);
    }

    public static Account getAccountWithToken(String token) throws AlphanahBaseException {
        return accountService.get(token);
    }

    public static void merchantVerify(String token) throws AlphanahBaseException {
        if (accountService.get(token).isMerchant())
            return;
        throw AuthException.roleNotAllowed();
    }

    public static void customerVerify(String token) throws AlphanahBaseException {
        if (accountService.get(token).isCustomer())
            return;
        throw AuthException.roleNotAllowed();
    }

}
