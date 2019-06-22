package com.valent.banksample.repository;

import com.valent.banksample.model.AccountNotFoundException;
import com.valent.banksample.model.AccountObject;
import com.valent.banksample.model.ExistingAccountNumberException;
import com.valent.banksample.model.InsufficientFundsException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    private AccountRepository subject;

    @Before
    public void setUp() throws Exception {
        subject = new AccountRepository();

        Map<String, AccountObject> hashMap = new HashMap<>();
        AccountObject johnDoe = new AccountObject("100", "John", "Doe", 30.0, null);
        hashMap.put("100", johnDoe);

        AccountObject samJohnson = new AccountObject("101", "Samantha", "Johnson", 100.0, "samantha.johnson@aol.com");
        hashMap.put("101", samJohnson);

        AccountObject jimJames = new AccountObject("102", "Jimmy", "James", 10.0, null);
        hashMap.put("102", jimJames);


        ReflectionTestUtils.setField(subject, "accountRepo", hashMap);
    }

    @Test
    public void shouldRetrieveAccountInfoForJohn() throws AccountNotFoundException {
        AccountObject returnedAccount = subject.getAccount("100");
        assertEquals("100", returnedAccount.getAccountNumber());
        assertEquals("John", returnedAccount.getFirstName());
        assertEquals("Doe", returnedAccount.getLastName());
        assertEquals(30.0, returnedAccount.getBalance(), 0.01);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowAnExceptionIfAccountIsNotFoundForGetAccount() throws AccountNotFoundException {
        subject.getAccount("51242");
    }

    @Test
    public void shouldRetrieveAccountBalance() throws AccountNotFoundException {
        assertEquals(30.0, subject.getBalance("100"), 0.01);
        assertEquals(100.0, subject.getBalance("101"), 0.01);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowAnExceptionIfAccountIsNotFoundForGetBalance() throws AccountNotFoundException {
        subject.getBalance("234234");
    }

    @Test
    public void testDeposit() throws AccountNotFoundException {
        double balance = subject.getBalance("100");
        subject.deposit("100", 25.0);
        assertEquals(balance + 25.0, subject.getBalance("100"), 0.01);
    }

    @Test(expected = AccountNotFoundException.class)
    public void depositShouldThrowExceptionIfAccountNotFound() throws AccountNotFoundException {
        subject.deposit("1999", 10.0);
    }

    @Test
    public void withdrawMoneyHappyPath() throws AccountNotFoundException, InsufficientFundsException {
        subject.withdraw("100", 5.0);
        assertEquals(25.0, subject.getBalance("100"), 0.01);
    }

    @Test(expected = AccountNotFoundException.class)
    public void withdrawShouldThrowExceptionIfAccountNotFound() throws AccountNotFoundException, InsufficientFundsException {
        subject.withdraw("1999", 10.0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void withdrawInsufficientFunds() throws AccountNotFoundException, InsufficientFundsException{
        subject.withdraw("100", 40.0);

    }

    @Test
    public void createAccountHappyPath() throws AccountNotFoundException, ExistingAccountNumberException {
        // Creating a sample account
        AccountObject chrisNellis = new AccountObject("103","Chris", "Nellis", 250.0,"Christopher.Nellis@bankofamerica.com");

        subject.createAccount(chrisNellis); // act
        assert(subject.getAccount("103").equals(chrisNellis)); // assert
    }

    @Test(expected = ExistingAccountNumberException.class)
    public void createExistingAccount() throws ExistingAccountNumberException{
        // Creating a sample account
        AccountObject chrisNellis = new AccountObject("102","Chris", "Nellis", 250.0,"Christopher.Nellis@bankofamerica.com");

        subject.createAccount(chrisNellis); // act

    }
}