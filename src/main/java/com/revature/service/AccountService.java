package com.revature.service;

import com.revature.model.Account;
import com.revature.persistance.AccountDAO;
import com.revature.view.AccountMenu;
import org.apache.log4j.Logger;

public class AccountService {
    static final Logger logger = Logger.getLogger(AccountService.class);
    AccountDAO accountDAO;
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean createAccount(int userID, double balance, String accountType) {
        Account newAccount = new Account(balance,accountType,userID);
        accountDAO.insert(newAccount);
        return true;
    }

    public Account[] getAllAccounts(int userID) {
        return accountDAO.selectAll(userID);
    }

    public void deposit(int userID, double depositAmount, int accountNum) {
        Account accountToUpdate = accountDAO.selectBy(Integer.toString(accountNum));
        if(accountToUpdate == null){
            logger.warn("You do not have access to this account");
        }else {
            Double balance = accountToUpdate.getBalance() + depositAmount;
            accountDAO.update(accountToUpdate, "balance", balance.toString());
        }
    }

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
