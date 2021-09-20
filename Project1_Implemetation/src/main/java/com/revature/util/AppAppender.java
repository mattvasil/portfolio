package com.revature.util;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class AppAppender {
    private final static PatternLayout forExecution = new PatternLayout("%p : %d{yyyy-MM-dd HH:mm:ss} : %m%n");

    public static void fileAppender() {
        FileAppender fileAppender = new org.apache.log4j.FileAppender();
        fileAppender.setThreshold(Level.ALL);
        fileAppender.setLayout(forExecution);
        fileAppender.setFile("C:\\Users\\matth\\Repositories\\myRepo\\My Projects\\Project1_Implemetation\\logs\\logger.txt");
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);
    }
}
