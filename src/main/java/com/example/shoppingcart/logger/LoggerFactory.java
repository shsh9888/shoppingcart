package com.example.shoppingcart.logger;

import java.util.HashMap;

public  abstract class LoggerFactory {
    private static Logger logger;
    public final static String INFO = "info";
    public final static String DEBUG = "debug";
    public final static String ERROR = "error";

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
