package com.revature.persistance;

import com.revature.model.Account;
import com.revature.service.AccountService;
import com.revature.view.AccountMenu;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

public class AccountDAOTest {

    public static void main(String[] args) {

    }

    @Test
    public void insertAccountTest(){
        Account testAccount = new Account(1000, "Checking", 30);
        AccountDAO accountDAO = new AccountDAO();

        accountDAO.insert(testAccount);
    }

    @Test
    public void selectAllTest(){
        AccountDAO accountDAO = new AccountDAO();
        AccountService accountService = new AccountService(accountDAO);
        AccountMenu accountMenu = new AccountMenu(accountService);


        accountMenu.showAccounts(30);
    }

    @Test
    public void depositAccountTest(){
        AccountDAO accountDAO = new AccountDAO();
        AccountService accountService = new AccountService(accountDAO);
        AccountMenu accountMenu = new AccountMenu(accountService);

        accountMenu.deposit(30);
    }
}
