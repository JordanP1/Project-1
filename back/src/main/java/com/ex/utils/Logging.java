package com.ex.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
    private static Logger logger = LogManager.getLogger();

    public static Logger getLogger() { return  logger; }
}
