package tech.zmario.school.api.command;

import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.SchoolCore;

import java.util.Scanner;

/**
 * Abstract class for commands. Every command should extend this class.
 */
public abstract class Command {

    /**
     * Core of the school application.
     */
    protected final SchoolCore schoolCore;
    /**
     * Name of the command.
     */
    protected final String name;
    /**
     * Description of the command.
     */
    protected final String description;

    /**
     * Creates a new command with the given name.
     *
     * @param schoolCore  Core of the school application.
     * @param name        Name of the command.
     * @param description Description of the command.
     */
    protected Command(SchoolCore schoolCore, String name, String description) {
        this.schoolCore = schoolCore;
        this.name = name;
        this.description = description;
    }

    /**
     * Validates the selection.
     *
     * @param logger  Logger to log errors.
     * @param scanner Scanner to read user input.
     * @param maxSize Maximum size of the selection.
     * @return validated selection.
     */
    protected static int validateSelection(Logger logger, Scanner scanner, int maxSize) {
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

    /**
     * Executes the command.
     *
     * @param scanner Scanner to read user input.
     */
    public abstract void execute(Scanner scanner);

    /**
     * Retrieves the name of the command.
     *
     * @return Name of the command.
     */
    public String name() {
        return name;
    }

    /**
     * Retrieves the description of the command.
     *
     * @return Description of the command.
     */
    public String description() {
        return description;
    }
}
