package tech.zmario.school.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.SchoolCore;
import tech.zmario.school.api.command.Command;
import tech.zmario.school.api.exercise.Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExerciseCommand extends Command {

    private static final Logger LOGGER = LogManager.getLogger(ExerciseCommand.class);
    private static final String COMMAND_NAME = "exercise";
    private static final String COMMAND_DESCRIPTION = "Starts an exercise";

    public ExerciseCommand(SchoolCore schoolCore) {
        super(schoolCore, COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(Scanner scanner) {
        LOGGER.info("Please select the exercise you want to start.");
        Map<Integer, Exercise> exercises = new HashMap<>();

        int id = 0;

        for (Exercise exercise : schoolCore.exerciseService().exercises()) {
            LOGGER.info("{}. {}", ++id, exercise.name());
            exercises.put(id, exercise);
        }

        LOGGER.info("{}. Go back to the help page.", ++id);

        int selection = validateSelection(LOGGER, scanner, exercises.size() + 1);

        if (selection == id) {
            schoolCore.commandService().showHelpPage(scanner);
            return;
        }
        Exercise exercise = exercises.get(selection);

        LOGGER.info("> Starting exercise: {}\n", exercise.name());

        exercise.solve(scanner);

        LOGGER.info("> Exercise completed.\n");

        execute(scanner);
    }
}
