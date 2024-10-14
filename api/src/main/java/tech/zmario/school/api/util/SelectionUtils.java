package tech.zmario.school.api.util;

import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Utility class for selection validation.
 */
public class SelectionUtils {

    private SelectionUtils() {
    }

    /**
     * Validates the selection.
     *
     * @param logger  Logger to log errors.
     * @param scanner Scanner to read user input.
     * @param maxSize Maximum size of the selection.
     * @return validated selection.
     */
    public static int validateSelection(Logger logger, Scanner scanner, int maxSize) {
        int selection;
        try {
            System.out.print("Insert selection > ");
            selection = Integer.parseInt(scanner.nextLine());

            if (selection < 1 || selection > maxSize) throw new NumberFormatException();
        } catch (Exception e) {
            logger.error("Invalid selection. Please try again.");

            return validateSelection(logger, scanner, maxSize);
        }

        return selection;
    }
}
