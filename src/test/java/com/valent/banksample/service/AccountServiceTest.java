package com.valent.banksample.service;

import com.valent.banksample.model.ExistingAccountNumberException;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceTest {
    private AccountService subject;

    @Before
    public void setUp(){
        subject = new AccountService();
    }


    @Test
    public void createAccount() throws ExistingAccountNumberException, MissingParameterException {
        double amount;
        subject.createAccount("103","Sean", "Valent", 0.0,"");
    }
}
