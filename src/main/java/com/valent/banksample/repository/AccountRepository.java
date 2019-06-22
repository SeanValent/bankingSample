package com.valent.banksample.repository;

import com.valent.banksample.model.AccountNotFoundException;
import com.valent.banksample.model.AccountObject;
import com.valent.banksample.model.ExistingAccountNumberException;
import com.valent.banksample.model.InsufficientFundsException;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class AccountRepository {
    private HashMap<String, AccountObject> accountRepo = new HashMap<>();

    @PostConstruct
    public void init() {
        AccountObject johnDoe = new AccountObject("100", "John", "Doe", 30.0, null);
        accountRepo.put("100", johnDoe);

        AccountObject samJohnson = new AccountObject("101", "Samantha", "Johnson", 100.0, "samantha.johnson@aol.com");
        accountRepo.put("101", samJohnson);

        AccountObject jimJames = new AccountObject("102", "Jimmy", "James", 10.0, null);
        accountRepo.put("102", jimJames);
    }

    public AccountObject getAccount(String accountNumber) throws AccountNotFoundException {
        AccountObject account = accountRepo.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("");
        }

        return account;
    }

    public double getBalance(String accountNumber) throws AccountNotFoundException {
        AccountObject accountObject = accountRepo.get(accountNumber);
        if (accountObject == null) {
            throw new AccountNotFoundException("");
        } else {
            return accountObject.getBalance();
        }
    }


    public void deposit(String accountNumber, double amountToDeposit) throws AccountNotFoundException {
        AccountObject object = accountRepo.get(accountNumber);
        if (object == null) {
            throw new AccountNotFoundException("");
        }
        object.setBalance(object.getBalance() + amountToDeposit);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        AccountObject object = accountRepo.get(accountNumber);
        if (object == null) {
            throw new AccountNotFoundException("");
        } else {
            if(object.getBalance() - amount < 0) {
                throw new InsufficientFundsException();
            }
            object.setBalance(object.getBalance() - amount);
        }
    }

    public void createAccount(AccountObject newAccount) throws ExistingAccountNumberException {
        if (accountRepo.get(newAccount.getAccountNumber()) != null){
            throw new ExistingAccountNumberException("Exception: Account already exists for account #"+ newAccount.getAccountNumber());
        } else {
            accountRepo.put(newAccount.getAccountNumber(), newAccount);
        }
    }


}
