package tech.zmario.school.api.exercise;

import java.util.Scanner;

/**
 * Interface for exercises. Every exercise should implement this interface.
 */
public abstract class Exercise {

    /**
     * Name of the exercise.
     */
    protected final String name;
    /**
     * Description of the exercise.
     */
    protected final String description;

    /**
     * Creates a new exercise with the given name.
     *
     * @param name        Name of the exercise.
     * @param description Description of the exercise.
     */
    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Starts the execution of the exercise.
     *
     * @param args    Arguments directly from the command line.
     *                This can be used to pass additional information to the exercise.
     * @param scanner Scanner to read user input.
     */
    public abstract void solve(Scanner scanner);

    /**
     * Retrieves the name of the exercise.
     *
     * @return Name of the exercise.
     */
    public String name() {
        return name;
    }

    /**
     * Retrieves the description of the exercise.
     *
     * @return Description of the exercise.
     */
    public String description() {
        return "No description available.";
    }
}