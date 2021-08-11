package com.revature.view;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class TransactionMenu {
    //Class variables scanner and logger
    Scanner scanner = new Scanner(System.in);
    static final Logger logger = Logger.getLogger(TransactionMenu.class);

    //pointer to transactionService
    private TransactionService transactionService;

    //constructor
    public TransactionMenu(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Takes input from user for transaction. Makes sure inputs fall within constrains:, No negatives etc.
     * accountTo, accountFrom: int: Ids for account involved in the transfer
     * amount:  double, Amount transferred
     * calls Transaction Service Transfer method
     * @param userID Takes userID from User object
     */
    public void transfer(int userID) {
        int accountTo, accountFrom;
        double amount;
        System.out.print("What account number is receiving funds:");
        String input = scanner.nextLine();

        try{
            accountTo = Integer.parseInt(input);
            if(accountTo <= 0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            logger.warn("This is not a valid account number.");
            return;
        }

        System.out.print("What account number is sending funds:");
        input = scanner.nextLine();

        try{
            accountFrom = Integer.parseInt(input);
            if(accountFrom <= 0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            logger.warn("This is not a valid account number.");
            return;
        }

        System.out.print("How much is being transferred:");
        input = scanner.nextLine();

        try{
            amount = Double.parseDouble(input);
            if(amount <= 0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            logger.warn("Please insert a flat positive number. ex: $1,000 is inputted as 1000");
            return;
        }

        transactionService.transfer(amount, accountFrom, accountTo);
        System.out.println("Transfer Successful.");
    }

    /**
     * calls Transaction Service GetAllTransaction method
     * iterates through an array and displays all objects
     * @param userID    User ID given by user object
     * @param accountService    accountService object
     */
    public void viewTransactionHistory(int userID, AccountService accountService) {
        int accountId;
        System.out.print("What account number do you want to view:");
        String input = scanner.nextLine();

        try{
            accountId = Integer.parseInt(input);
            if(accountId <= 0){
                throw new NumberFormatException();
            }
            if( accountService.viewSingleAccount(userID,accountId) == null){
                throw new NoSuchFieldException();
            }
        }catch(NumberFormatException e){
            logger.warn("This is not a valid account number.");
            return;
        }catch (NoSuchFieldException e){
            logger.warn("You do not have access to this account");
            return;
        }


        System.out.println("Here are all oo this accounts transactions:");
        Transaction[] transactions = transactionService.getAllTransactions(userID, accountId);
        for(Transaction t : transactions){
            t.toString();
        }
    }
}
