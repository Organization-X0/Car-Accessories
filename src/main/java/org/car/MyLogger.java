package org.car;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class MyLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogger.class);

    public void logInfo(String logging) {
        LOGGER.info("\033[44m\033[97mThis text is very white on a blue background!\033[0m");
        LOGGER.info("\033[32m{}\033[0m",logging);
    }

}
