package com.revature.view;

import com.revature.persistance.AccountDAO;
import com.revature.persistance.TransactionDAO;
import com.revature.service.AccountService;
import com.revature.service.TransactionService;
import com.revature.service.UserService;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class UserMenu {
    //Class variables
    Scanner scanner = new Scanner(System.in);
    static final Logger logger = Logger.getLogger(UserMenu.class);
    private final UserService userService;
    private final AccountMenu accountMenu;
    private final TransactionMenu transactionMenu;
    //Added Other two Views because I made UserMenu the main view

    //Constructor
    public UserMenu(UserService userService) {
        this.userService = userService;
        AccountDAO accountDAO = new AccountDAO();
        AccountService accountService = new AccountService(accountDAO);
        accountMenu = new AccountMenu(accountService);

        TransactionDAO transactionDAO = new TransactionDAO();
        TransactionService transactionService = new TransactionService(transactionDAO, accountService);
        transactionMenu = new TransactionMenu(transactionService);
    }

    /**
     * Login menu
     * allows user to choose to login or register a new user
     */
    public void initialMenu(){
        System.out.println("1) Login \n" +
                "2) Register\n" +
                "0) Exit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                login(); break;
            case "2":
                register(); break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Please type one of these responses:");
                initialMenu();
        }
    }

    /**
     * login menu
     * compares input username and password against database users
     * if match, calls loggedMenu()
     * if no match, calls initialMenu()
     */
    private void login() {
        System.out.print("Please insert your username:");
        String un = scanner.nextLine();
        System.out.print("Please insert your password:");
        String pw = scanner.nextLine();
        if(userService.login(un, pw)){
            System.out.println("You have been Logged In! \nHave Fun!");
            loggedInMenu();
        }else{
            System.out.println("Incorrect Username or password");
            initialMenu();
        }
    }

    /**
     * register menu
     * gathers firstname, lastname, username and password
     * confirms user password
     * calls userService.register()
     * if true, calls loggedMenu()
     * if false, recursively calls register()
     */
    private void register() {
        System.out.print("Please enter your first name:");
        String fn = scanner.nextLine();
        System.out.print("Please enter your last name:");
        String ln = scanner.nextLine();
        System.out.print("Please enter a username:");
        String un = scanner.nextLine();
        System.out.print("Please enter a password:");
        String pw = scanner.nextLine();
        System.out.print("Please confirm your password:");
        String conPW = scanner.nextLine();
        if(userService.register(fn, ln, un, pw, conPW)){
            System.out.println("Account Created! Have Fun!");
            loggedInMenu();
        }else{
            logger.warn("Username Already Taken.");
            register();
        }
    }

    /**
     * Main menu used for user.
     * calls corresponding Menu classes and methods according to user input
     * finally calls itself until user exits
     */
    private void loggedInMenu() {
        System.out.println("\nWhat would you like to do?" +
                "\n1)Create a New Bank Account." +
                "\n2)View All Accounts." +
                "\n3)Deposit Funds." +
                "\n4)Withdraw Funds." +
                "\n5)Transfer Funds." +
                "\n6)View Balance of an Account." +
                "\n7)View Transaction History" +
                "\n0)Exit.");
        String input = scanner.nextLine();

        switch(input){
            case "0":
                System.exit(0);
            case "1":
                accountMenu.createAccount(userService.getUserID()); break;
            case "2":
                accountMenu.showAccounts(userService.getUserID()); break;
            case"3":
                accountMenu.deposit(userService.getUserID()); break;
            case"4":
                accountMenu.withdraw(userService.getUserID()); break;
            case"5":
                transactionMenu.transfer(userService.getUserID()); break;
            case "6":
                accountMenu.viewSingleAccount(userService.getUserID()); break;
            case"7":
                transactionMenu.viewTransactionHistory(userService.getUserID(), accountMenu.accountService); break;
        }
        loggedInMenu();
    }
}
