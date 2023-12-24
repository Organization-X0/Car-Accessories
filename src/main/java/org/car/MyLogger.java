package org.car;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class MyLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogger.class);

    public void log(String logging) {
        LOGGER.info(logging);
    }

}
