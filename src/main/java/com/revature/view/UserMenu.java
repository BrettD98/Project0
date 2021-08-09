package com.revature.view;

import com.revature.service.UserService;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    private final UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    public void initialMenu(){
        System.out.println("1) Login \n" +
                "2) Register\n" +
                "0) Exit");
        String input = scanner.nextLine();
        switch(input){
            case "1":
                login();
                //loggedInMenu();
                break;
            case "2":
                register(); break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Please type one of these responses:");
                initialMenu();
        }
    }

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

    private void register() {
    }

    private void loggedInMenu() {
        System.out.println();
    }
}
