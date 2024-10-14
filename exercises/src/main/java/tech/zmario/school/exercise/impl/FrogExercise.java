package tech.zmario.school.exercise.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.exercise.Exercise;
import tech.zmario.school.api.util.SelectionUtils;

import java.util.Scanner;

/**
 * Exercise to solve the frog problem.
 */
public class FrogExercise extends Exercise {

    private static final Logger LOGGER = LogManager.getLogger(FrogExercise.class);

    // Constants for the exercise
    private static final int DEFAULT_HEIGHT = 21;
    private static final int DEFAULT_DAY_LEAP = 3;
    private static final int DEFAULT_NIGHT_SLIP = 2;

    private static final String DESCRIPTION_VALUE = """
            Una rana deve raggiungere la cima di un pozzo alto %d metri e decide di scalarlo verticalmente.
            Se di giorno scala %d metri e di notte scende di %d, in quanti giorni riuscirà ad arrivare sulla cima?
            """.formatted(DEFAULT_HEIGHT, DEFAULT_DAY_LEAP, DEFAULT_NIGHT_SLIP);

    /**
     * Creates a new frog exercise.
     */
    public FrogExercise() {
        super("frog", DESCRIPTION_VALUE);
    }

    @Override
    public void solve(Scanner scanner) {
        int days = 0;
        int height = 0;

        LOGGER.info("Insert the height of the well");

        int wellHeight = SelectionUtils.validateSelection(LOGGER, scanner, Integer.MAX_VALUE);

        LOGGER.info("Insert the leap of the frog during the day");

        int dayLeap = SelectionUtils.validateSelection(LOGGER, scanner, Integer.MAX_VALUE);

        LOGGER.info("Insert the slip of the frog during the night");

        int nightSlip = SelectionUtils.validateSelection(LOGGER, scanner, Integer.MAX_VALUE);

        if (dayLeap <= nightSlip) {
            LOGGER.error("The frog will never reach the top of the well!");
            return;
        }

        while (height < wellHeight) {
            days++;
            height += dayLeap;

            if (height >= wellHeight) break;

            height -= nightSlip;
        }

        LOGGER.info("The frog needs {} day{} to reach the top of the well.", days, days == 1 ? "" : "s");
    }
}
