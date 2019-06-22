package com.valent.banksample.controller;

import com.valent.banksample.model.AccountNotFoundException;
import com.valent.banksample.model.AccountObject;
import com.valent.banksample.model.ExistingAccountNumberException;
import com.valent.banksample.model.InsufficientFundsException;
import com.valent.banksample.service.AccountService;
import com.valent.banksample.service.MissingParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class AccountController {
    private AccountService service;

    public AccountController(){
        service = new AccountService();
    }

    @PutMapping(value="/deposit")
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        service.deposit(accountNumber, amount);
    }

    @PutMapping(value="/withdraw")
    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        service.withdraw(accountNumber,amount);
    }

    @GetMapping(value="/getAccount")
    public AccountObject getAccount(String accountNumber) throws AccountNotFoundException {
        return service.getAccountObject(accountNumber);
    }

    @GetMapping(value="/getBalance")
    public Double getBalance(String accountNumber) throws AccountNotFoundException {
        return service.getBalance(accountNumber);
    }

    @PostMapping(value="/createAccount")
    public void createAccount(String accountNumber, String firstName, String lastName, double balance, String email) throws ExistingAccountNumberException, MissingParameterException {
        service.createAccount(accountNumber, firstName, lastName, balance, email);
    }
}
