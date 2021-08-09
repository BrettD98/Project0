package com.revature.model;

public class Transaction {
    private int transactionId;
    private int accountTo;
    private int accountFrom;
    private int amount;

    public Transaction() {
    }

    public Transaction(int accountTo, int accountFrom, int amount) {
        this.accountTo = accountTo;
        this.accountFrom = accountFrom;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }
    public int getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }
    public int getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
