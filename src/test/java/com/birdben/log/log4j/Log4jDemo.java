package com.birdben.log.log4j;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jDemo extends TestCase {

    private static Logger logger = Logger.getLogger(Log4jDemo.class);

    @Test
    public void testLog4jPrintMessage() {
        // 记录debug级别的信息
        logger.debug("This is debug message.");

        // 记录info级别的信息
        logger.info("This is info message.");

        // 记录error级别的信息
        logger.error("This is error message.");
    }
}