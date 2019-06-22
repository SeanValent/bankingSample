# Project Overview

This is a simple banking REST API for Bank of America. For this sample, account numbers are considered to be unique

### Functions

* Get account information by account number
* Get account balance by account number
* Deposit money to an account
	* This operation will affect the "get balance" operation
* Withdrawal money from an account
	* This operation will affect the "get balance" operation
* Create a new account

* The API will be populated with the following accounts on startup:
	* Account number: 100
		* First name: John
		* Last name: Doe
		* Balance: $30
	* Account number: 101
		* First name: Samantha
		* Last name: Johnson
		* Balance: $100
		* Email: samantha.johnson@aol.com
	* Account number: 102
		* First name: Jimmy
		* Last name: James
		* Balance: $10

		
* The aim is for the API to gracefully handle error cases
	
### What fields are in a Bank Account?

* Account number
* Balance
* First name
* Last name
* Email address
	* This field is optional, and might not be used in all accounts

