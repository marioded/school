package tech.zmario.school.exercise.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.exercise.Exercise;

import java.util.Scanner;

/**
 * Exercise to solve the frog problem.
 */
public class FrogExercise extends Exercise {

    private static final Logger LOGGER = LogManager.getLogger(FrogExercise.class);

    // Constants for the exercise
    private static final int WELL_HEIGHT = 21;
    private static final int DAY_LEAP = 3;
    private static final int NIGHT_SLIP = 2;

    private static final String DESCRIPTION_VALUE = """
            Una rana deve raggiungere la cima di un pozzo alto %d metri e decide di scalarlo verticalmente.
            Se di giorno scala %d metri e di notte scende di %d, in quanti giorni riuscirà ad arrivare sulla cima?
            """.formatted(WELL_HEIGHT, DAY_LEAP, NIGHT_SLIP);

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

        while (true) {
            height += DAY_LEAP;

            if (height >= WELL_HEIGHT) break;

            height -= NIGHT_SLIP;
            days++;
        }

        LOGGER.info("The frog needs {} days to reach the top of the well.", days);
    }
}
