package com.revature.model;

public class Transaction {
    private int transactionId;
    private int accountTo;
    private int accountFrom;
    private double amount;

    public Transaction() {
    }

    public Transaction(int accountTo, int accountFrom, double amount) {
        this.accountTo = accountTo;
        this.accountFrom = accountFrom;
        this.amount = amount;
    }

    public Transaction(int transactionId, int accountFrom, int accountTo, double amount) {
        this.transactionId = transactionId;
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
    public double getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        System.out.printf("Account from: [%d] \t Account to: [%d] \t Amount: [$%.2f]\n", accountFrom, accountTo, amount);
        return null;
    }
}
