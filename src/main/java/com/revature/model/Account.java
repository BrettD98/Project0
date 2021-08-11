package com.revature.model;

/**
 * Account Data Model
 * Overrides toString for better readability
 */

public class Account {
    private int accountID;
    private double balance;
    private String accountType; //TODO: make this an enum
    private int userID;

    public Account(int accountID, double balance, String accountType, int userID) {
        this.accountID = accountID;
        this.balance = balance;
        this.accountType = accountType;
        this.userID = userID;
    }

    public Account(double balance, String accountType, int userID) {
        this.balance = balance;
        this.accountType = accountType;
        this.userID = userID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        System.out.printf("Account Number: [%d] \t Account Balance: [$%.2f] \t Account Type: [%s]\n", accountID, balance, accountType);
        return null;
    }
}
