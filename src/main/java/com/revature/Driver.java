package com.revature;

import com.revature.persistance.UserDAO;
import com.revature.service.UserService;
import com.revature.view.UserMenu;

public class Driver {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserMenu userMenu = new UserMenu(userService);

        System.out.printf("-------------------------------------------------\n");
        System.out.printf("\t\t\tWelcome To The Code Bank\n");
        System.out.printf("-------------------------------------------------\n");

        userMenu.initialMenu();


    }
}
