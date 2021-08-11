package com.revature.service;

import com.revature.model.Transaction;
import com.revature.persistance.TransactionDAO;

public class TransactionService {
    private TransactionDAO transactionDAO;
    private AccountService accountService;

    public TransactionService(TransactionDAO transactionDAO, AccountService accountService) {
        this.transactionDAO = transactionDAO;
        this.accountService = accountService;
    }

    public void transfer(double amount, int accountFrom, int accountTo) {
        Transaction newTransaction = new Transaction(accountTo,accountFrom,amount);

        transactionDAO.insert(newTransaction);
        accountService.deposit(accountTo, amount, accountTo);
        accountService.deposit(accountFrom, -amount, accountFrom);
    }

    public Transaction[] getAllTransactions(int userID, int accountId) {
        return transactionDAO.selectAll(accountId);
    }
}
