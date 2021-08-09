package com.revature.util;

import org.apache.log4j.*;

public class LoggingUtil {
    static final Logger logger = Logger.getLogger(LoggingUtil.class);

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

        /*
            %m - print the user supplied message during the logging event
            %C - print the class that created the logging event HINT: use C{1} -> gives simple name
            %d - output the date of the logging event
            %p - output the priority of the logging event
            %n - new line
         */

    }

}
