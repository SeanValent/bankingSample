package com.valent.banksample.service;

import com.valent.banksample.model.AccountNotFoundException;
import com.valent.banksample.model.AccountObject;
import com.valent.banksample.model.ExistingAccountNumberException;
import com.valent.banksample.model.InsufficientFundsException;
import com.valent.banksample.repository.AccountRepository;

public class AccountService {
    private AccountRepository repository = new AccountRepository();

    public AccountService(){
        repository.init();
    }

    public AccountObject getAccountObject(String accountNumber) throws AccountNotFoundException {
        return repository.getAccount(accountNumber);
    }

    public double getBalance(String accountNumber) throws AccountNotFoundException{
        return repository.getBalance(accountNumber);
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException{
        repository.deposit(accountNumber,amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientFundsException{
        repository.withdraw(accountNumber,amount);
    }

    public void createAccount(String accountNumber, String firstName, String lastName, double balance, String email ) throws ExistingAccountNumberException, MissingParameterException {
        if (accountNumber.isEmpty()||firstName.isEmpty()||lastName.isEmpty()||balance <0.0){
            throw new MissingParameterException("Exception: Create Account requires Account Number, first name, last name, and balance");
        }
        AccountObject newObject = new AccountObject(accountNumber,firstName,lastName,balance,email);

        repository.createAccount(newObject);
    }

}
