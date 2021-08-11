package com.revature.service;

import com.revature.model.Transaction;
import com.revature.persistance.TransactionDAO;

public class TransactionService {
    //Class pointers to DAO and AccountService
    private TransactionDAO transactionDAO;
    private AccountService accountService;

    //Constructor
    public TransactionService(TransactionDAO transactionDAO, AccountService accountService) {
        this.transactionDAO = transactionDAO;
        this.accountService = accountService;
    }

    /**
     * Creates a new Transaction object
     * inserts it into Database
     * deposits and withdraws from accounts given
     * @param amount amount transferred
     * @param accountFrom accountID transferred from
     * @param accountTo accountID transferred to
     */
    public void transfer(double amount, int accountFrom, int accountTo) {
        Transaction newTransaction = new Transaction(accountTo,accountFrom,amount);

        transactionDAO.insert(newTransaction);
        accountService.deposit(accountTo, amount, accountTo);
        accountService.deposit(accountFrom, -amount, accountFrom);
    }

    /**
     * passes an array from DAO to Menu classes
     * @param userID userID from User Object
     * @param accountId accountID given from user input
     * @return Account array from DAO
     */
    public Transaction[] getAllTransactions(int userID, int accountId) {
        return transactionDAO.selectAll(accountId);
    }
}
