package com.revature.view;

import com.revature.model.Account;
import com.revature.persistance.UserDAO;
import com.revature.service.AccountService;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class AccountMenu {
    //Class variables Scanner, Logger
    static final Logger logger = Logger.getLogger(AccountMenu.class);
    Scanner scanner = new Scanner(System.in);
    //Pointer to accountService object
    AccountService accountService;

    //Constructor
    public AccountMenu(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Takes input from user for creating a new account, Checks against constraints
     * Calls Account Service create account method
     * @param userID UserID from user object
     * @return true if account has been created
     */
    public boolean createAccount(int userID) {
        String accountType = "default";
        double balance = 0.0;
        boolean loop = true;
        String input;

        do{
            System.out.print("What Type is this account:" +
                    "\n1)Checking." +
                    "\n2)Savings." +
                    "\n3)Debit.\n");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    accountType = "Checking";
                    loop = false;
                    break;
                case "2":
                    accountType = "Savings";
                    loop = false;
                    break;
                case "3":
                    accountType = "Debit";
                    loop = false;
                    break;
                default:
                    System.out.println("Please input the account type");
                    break;
            }
        }while(loop);
        loop = true;

        do{
            System.out.print("How much money is this account starting with:");
            input = scanner.nextLine();
            try{
                balance = Double.parseDouble(input);
                if(balance <= 0){
                    throw new NumberFormatException();
                }
                loop = false;
            }catch(NumberFormatException e){
                System.out.println("Please input balance as a flat positive value:\n" +
                        "ex: $1,000.00 is input as 1000.00");
            }
        }while(loop);

        accountService.createAccount(userID, balance, accountType);
        return false;
    }

    /**
     * Calls account service get all accounts method
     * iterates through an array and displays all Accounts
     * @param userID
     */
    public void showAccounts(int userID) {
        System.out.println("Here are all of your active accounts:");
        Account[] accounts = accountService.getAllAccounts(userID);
        for(Account a : accounts){
            a.toString();
        }
    }

    /**
     * takes input for deposit and checks against constraints
     * makes sure the account exists
     * calls service deposit method
     * @param userID
     */
    public void deposit(int userID) {
        int accountNum;
        boolean exists = false;
        Account[] accounts = accountService.getAllAccounts(userID);



        double depositAmount;
        System.out.print("Which account number to deposit to:");
        String input = scanner.nextLine();
        try {
            accountNum = Integer.parseInt(input);
        }catch(NumberFormatException e){
            logger.warn("\nThis is not a valid account number.\n");
            return;
        }

        for(Account a : accounts){
            if(a.getAccountID() == accountNum){
                exists = true;
            }
        }

        if(!exists){
            logger.warn("\nYou do not have access to that account\n");
            return;
        }

        System.out.print("How much are you depositing:");
        input = scanner.nextLine();

        try {
            depositAmount = Double.parseDouble(input);
            if(depositAmount <= 0){
                throw new NumberFormatException("Less than or equal to 0");
            }
        }catch(NumberFormatException e){
            logger.warn("\nThis is not a valid deposit.\n");
            return;
        }
        accountService.deposit(userID, depositAmount, accountNum);
        System.out.println("Deposit Successful.");
    }

    /**
     * does the opposite of deposit
     * makes sure the account exists
     * makes sure the account has enough to withdraw
     * calls service deposit method (Withdraw is a deposit but negative)
     * displays ascii art
     * @param userID
     */
    public void withdraw(int userID) {
        int accountNum;
        boolean exists = false;
        Account[] accounts = accountService.getAllAccounts(userID);



        double withdrawAmount;
        System.out.print("Which account number to withdraw from:");
        String input = scanner.nextLine();
        try {
            accountNum = Integer.parseInt(input);
        }catch(NumberFormatException e){
            logger.warn("\nThis is not a valid account number.\n");
            return;
        }
        Account account = null;
        for(Account a : accounts){
            if(a.getAccountID() == accountNum){
                exists = true;
                account = a;
            }
        }

        if(!exists){
            logger.warn("\nYou do not have access to that account\n");
            return;
        }

        System.out.print("How much are you withdrawing:");
        input = scanner.nextLine();

        try {
            withdrawAmount = -(Double.parseDouble(input));
            if(withdrawAmount >= 0){
                throw new NumberFormatException("Greater than or equal to 0");
            }
        }catch(NumberFormatException e){
            logger.warn("\nThis is not a valid withdraw.\n");
            return;
        }

        if(withdrawAmount > account.getBalance()){
            logger.warn("Cannot withdraw more than current balance");
        }

        accountService.deposit(userID, withdrawAmount, accountNum);
        System.out.println("___________________________________\n" +
                "|#######====================#######|\n" +
                "|#(1)*UNITED STATES OF AMERICA*(1)#|\n" +
                "|#**          /===\\   ********  **#|\n" +
                "|*# {G}      | (\") |             #*|\n" +
                "|#*  ******  | /v\\ |    O N E    *#|\n" +
                "|#(1)         \\===/            (1)#|\n" +
                "|##=========ONE DOLLAR===========##|\n" +
                "------------------------------------\n");

    }

    /**
     * Displays a single account of the users choice
     * checks against user have access
     * calls service get single account method
     * @param userID
     */
    public void viewSingleAccount(int userID) {
        int accountNum;
        System.out.print("Which account balance to view:");
        String input = scanner.nextLine();
        try {
            accountNum = Integer.parseInt(input);
        }catch(NumberFormatException e){
            logger.warn("\nThis is not a valid account number.\n");
            return;
        }

        Account account = accountService.viewSingleAccount(userID, accountNum);
        if(account == null || account.getUserID() != userID){
            logger.warn("You do not have access to this account.");
        }else {
            System.out.printf("The Account Balance is currently : %.2f\n", account.getBalance());
        }
    }
}
