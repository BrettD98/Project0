package com.revature.persistance;

import com.revature.model.User;
import com.revature.util.LoggingUtil;
import org.apache.log4j.*;
import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {

    static final Logger logger = Logger.getLogger(UserDAOTest.class);

    public static void main(String[] args) {
        PatternLayout layout = new PatternLayout("[%p] || %d || %m%n");

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);

        FileAppender fileAppender = new FileAppender();
        fileAppender.setThreshold(Level.ALL);
        fileAppender.setLayout(layout);
        fileAppender.setFile("src/main/logs/log.txt");
        fileAppender.setAppend(false);
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);

        //findUserTest();
        //deleteUserTest();
        //updateUserTest();
        //selectAllUserTest();
    }


    @Test
    public static void findUserTest(){
        User userTest = new User("Brett", "Davis", "1234", "BDavis");
        UserDAO dao = new UserDAO();

        dao.insert(userTest);
        dao.insert(userTest);
        User newUser = dao.selectBy("BDavis");

        //cannot compare object to object b/c userTest doesn't have userId, given by db
        Assert.assertEquals(userTest.getFirstName(), newUser.getFirstName());
        Assert.assertEquals(userTest.getLastName(), newUser.getLastName());
        Assert.assertEquals(userTest.getPassword(), newUser.getPassword());
        Assert.assertEquals(userTest.getUsername(), newUser.getUsername());

    }

    @Test
    public static void deleteUserTest(){
        User userTest = new User("Brett", "Davis", "1234", "BDavis");
        UserDAO dao = new UserDAO();

        //Prior Test shows that insert works
        dao.insert(userTest);

        dao.delete((userTest));

        //prior test shows that selectBy works
        Assert.assertEquals(null, dao.selectBy("BDavis"));
    }

    @Test
    private static void updateUserTest() {
        User userTest = new User("Brett", "Davis", "1234", "BDavis");
        UserDAO dao = new UserDAO();
        dao.delete((userTest));
        dao.insert(userTest);

        dao.update(userTest, "first_name", "George");
        dao.update(userTest, "last_name", "Washington");
        dao.update(userTest, "username", "MrFirstPresident");
        userTest.setUsername("MrFirstPresident");
        dao.update(userTest, "password", "WarWinner55");

        Assert.assertEquals("MrFirstPresident", dao.selectBy("MrFirstPresident").getUsername());
        Assert.assertEquals("George", dao.selectBy("MrFirstPresident").getFirstName());
        Assert.assertEquals("Washington", dao.selectBy("MrFirstPresident").getLastName());
        Assert.assertEquals("WarWinner55", dao.selectBy("MrFirstPresident").getPassword());
        dao.delete((userTest));
    }

    @Test
    public static void selectAllUserTest(){
        UserDAO dao = new UserDAO();
        User userTest = new User("Brett", "Davis", "1234", "BDavis");
        dao.insert(userTest);
        userTest = new User("Brett", "Davis", "1234", "pauky");
        dao.insert(userTest);
        userTest = new User("Brett", "Davis", "1234", "Peanut");
        dao.insert(userTest);

        User[] arr = dao.selectAll();

        System.out.println(arr[0].toString());
    }
}