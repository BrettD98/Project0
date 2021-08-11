package com.revature;

import com.revature.persistance.UserDAO;
import com.revature.service.UserService;
import com.revature.view.UserMenu;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Driver {
    public static void main(String[] args) {
        /*
        Create the logger for this program
         */
        PatternLayout layout = new PatternLayout("[%p] || %d || %m%n");
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);

        //Create a MVC
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserMenu userMenu = new UserMenu(userService);

        //Splash Screen
        System.out.printf("-------------------------------------------------\n");
        System.out.printf("\t\t\tWelcome To The Code Bank\n");
        System.out.printf("-------------------------------------------------\n");

        //Call to initial menu
        userMenu.initialMenu();


    }
}
