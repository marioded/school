package tech.zmario.school.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.SchoolCore;
import tech.zmario.school.api.command.Command;
import tech.zmario.school.api.util.SelectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HelpCommand extends Command {

    private static final Logger LOGGER = LogManager.getLogger(HelpCommand.class);
    private static final String COMMAND_NAME = "help";
    private static final String COMMAND_DESCRIPTION = "Shows the help page";

    public HelpCommand(SchoolCore schoolCore) {
        super(schoolCore, COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(Scanner scanner) {
        LOGGER.info("Available commands:");
        Map<Integer, Command> commands = new HashMap<>();

        int id = 0;
        for (Command command : schoolCore.commandService().commands()) {
            LOGGER.info("{}. {} ({})", ++id, command.name(), command.description());
            commands.put(id, command);
        }

        LOGGER.info("Please select a command.");

        int selection = SelectionUtils.validateSelection(LOGGER, scanner, commands.size());
        Command command = commands.get(selection);

        LOGGER.info("> Selected command: {}\n", command.name());

        command.execute(scanner);
    }
}
