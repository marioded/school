package tech.zmario.school;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class of the application.
 */
public class Application {

    /**
     * Logger for the application.
     */
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        new SchoolCoreImpl();
    }
}
