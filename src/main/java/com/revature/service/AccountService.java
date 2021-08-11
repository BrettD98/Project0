package com.revature.service;

import com.revature.model.Account;
import com.revature.persistance.AccountDAO;
import com.revature.view.AccountMenu;
import org.apache.log4j.Logger;


public class AccountService {
    //Class logger and DAO
    static final Logger logger = Logger.getLogger(AccountService.class);
    AccountDAO accountDAO;
    //Constructor
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * Creates a user object and passes it to DAO layer
     * @param userID id of the user creating the account
     * @param balance balance that the account begins with
     * @param accountType type of account (Checking, Savings, Debit)
     * @return returns true
     */
    public boolean createAccount(int userID, double balance, String accountType) {
        Account newAccount = new Account(balance,accountType,userID);
        accountDAO.insert(newAccount);
        return true;
    }

    /**
     * Passes Account Array from DAO to Menu
     * @param userID userID from user object
     * @return Account arrray from DAO
     */
    public Account[] getAllAccounts(int userID) {
        return accountDAO.selectAll(userID);
    }

    /**
     * Gets account from DAO, makes sure the account exists
     * gets current balance adds deposit amount
     * Calls DAO to update account object
     * @param userID UserID from User Object
     * @param depositAmount amount being deposited
     * @param accountNum account number being deposited into
     */
    public void deposit(int userID, double depositAmount, int accountNum) {
        Account accountToUpdate = accountDAO.selectBy(Integer.toString(accountNum));
        if(accountToUpdate == null){
            logger.warn("You do not have access to this account");
        }else {
            Double balance = accountToUpdate.getBalance() + depositAmount;
            accountDAO.update(accountToUpdate, "balance", balance.toString());
        }
    }

    /**
     * Gets account from DAO view
     * if it exits passes it to Menu, if not returns null
     * @param userID
     * @param accountNum Account number that user wants to view
     * @return account object or null
     */
    public Account viewSingleAccount(int userID, int accountNum) {
        Account account = accountDAO.selectBy(Integer.toString(accountNum));
        if(account == null){
            logger.warn("You do not have access to this account");
            return null;
        }else {
            return account;
        }
    }
}
