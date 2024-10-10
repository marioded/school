package tech.zmario.school.api.service;

import tech.zmario.school.api.command.Command;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

/**
 * Service for commands. This service is responsible for registering and unregistering commands.
 */
public interface CommandService {

    void start();

    /**
     * Registers a command.
     *
     * @param command Command to register.
     * @return <code>true</code> if the command was successfully registered, <code>false</code> otherwise.
     */
    boolean register(Command command);

    /**
     * Unregisters a command.
     *
     * @param command Command to unregister.
     * @return <code>true</code> if the command was successfully unregistered, <code>false</code> otherwise.
     */
    boolean unregister(Command command);

    /**
     * Unregisters a command by its name.
     *
     * @param name Name of the command to unregister.
     * @return <code>true</code> if the command was successfully unregistered, <code>false</code> otherwise.
     */
    boolean unregister(String name);

    /**
     * Retrieves a command by its name.
     *
     * @param name Name of the command.
     * @return The command with the given name, or <code>null</code> if no such command exists.
     */
    Optional<Command> command(String name);

    /**
     * Retrieves all registered commands as an unmodifiable collection.
     *
     * @return All registered commands.
     */
    Collection<Command> commands();

    /**
     * Shows the help page menu.
     *
     * @param scanner Scanner to read user input.
     */
    void showHelpPage(Scanner scanner);
}
