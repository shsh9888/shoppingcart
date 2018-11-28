package com.example.shoppingcart.logger;

import java.util.HashMap;

/**
 * Factory Pattern implemnetation : Class to store and return different objects of the
 * logger when required
 */
public  abstract class LoggerFactory {
    private static Logger logger;
    public final static String INFO = "info";
    public final static String DEBUG = "debug";
    public final static String ERROR = "error";
    /**
     * Keeps track of the objects so that they will not be created again and again.
     */
    private static HashMap<String, Logger> loggerMap = new HashMap<>();

    public static Logger getLogger(String type) {
        if (loggerMap.containsKey(type)) {
            return loggerMap.get(type);
        }
        switch (type) {
            case "info":
                logger = new InfoLogger();
                loggerMap.put(type, logger);
                return logger;
            case "debug":
                logger = new DebugLogger();
                loggerMap.put(type, logger);
                return logger;
            case "error":
                logger = new ErrorLogger();
                loggerMap.put(type, logger);
                return logger;
        }
        return new InfoLogger();
    }
}
