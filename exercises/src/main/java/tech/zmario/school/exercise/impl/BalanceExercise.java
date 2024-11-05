package tech.zmario.school.exercise.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.exercise.Exercise;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Exercise to solve the balance problem.
 */
public class BalanceExercise extends Exercise {

    private static final Logger LOGGER = LogManager.getLogger(BalanceExercise.class);

    private static final String DESCRIPTION_VALUE = """
            This exercise simulates the fake money problem where one coin is fake among several coins in different groups.
            You must identify the fake coin with the minimum number of weighings.
            The fake coin is heavier than the others.
            """;

    private static final int FIRST_GROUP_SIZE = 3;
    private static final int SECOND_GROUP_SIZE = 3;
    private static final int THIRD_GROUP_SIZE = 2;

    public BalanceExercise() {
        super("frog-balance", DESCRIPTION_VALUE);
    }

    @Override
    public void solve(Scanner scanner) {
        int[][] groups = new int[3][];

        groups[Group.FIRST.index()] = new int[FIRST_GROUP_SIZE];
        groups[Group.SECOND.index()] = new int[SECOND_GROUP_SIZE];
        groups[Group.THIRD.index()] = new int[THIRD_GROUP_SIZE];

        // Get user input for the fake coin's position
        LOGGER.info("Insert the group number (1-3) with the fake money:");
        int fakeMoneyGroup = scanner.nextInt() - 1; // Convert to 0-based index

        LOGGER.info("Insert the position of the fake money in group (1-3 for groups 1 and 2, 1-2 for group 3):");
        int fakeMoneyPosition = scanner.nextInt() - 1; // Convert to 0-based index

        // Fill groups with real coins (1) and one fake coin (2)
        for (int[] group : groups) {
            Arrays.fill(group, 1); // Initialize all coins as real
        }
        groups[fakeMoneyGroup][fakeMoneyPosition] = 2; // Set the fake coin

        LOGGER.info("The groups are:");

        for (int[] group : groups) LOGGER.info(Arrays.toString(group));

        // Find the fake coin's position
        findFakeMoney(groups);
    }

    /**
     * Finds the position of the fake coin among the groups of coins.
     *
     * @param groups the groups of coins
     */
    private void findFakeMoney(int[][] groups) {
        LOGGER.info("Now finding the fake money position...");
        int numberOfWeighings = 0;

        LOGGER.info("First weighing: Comparing group 1 and group 2");
        numberOfWeighings++;

        int firstGroupWeight = Arrays.stream(groups[Group.FIRST.index()]).sum();
        int secondGroupWeight = Arrays.stream(groups[Group.SECOND.index()]).sum();

        if (firstGroupWeight == secondGroupWeight) { // Same weight, fake coin is in group 3
            LOGGER.info("The fake coin is in group 3.");
            LOGGER.info("Second weighing: Weighing coins in group 3.");
            numberOfWeighings++;

            LOGGER.info("The fake coin is the {} coin in group 3.",
                    (groups[Group.THIRD.index()][0] > groups[Group.THIRD.index()][1]) ?
                            Group.FIRST.name().toLowerCase() : Group.SECOND.name().toLowerCase());
        } else { // Different weight, fake coin is in a lighter group
            int suspectGroupIndex = firstGroupWeight < secondGroupWeight ? Group.SECOND.index() : Group.FIRST.index();
            LOGGER.info("The fake coin is in group {}.", suspectGroupIndex + 1);
            LOGGER.info("Second weighing: Comparing coins in the suspect group.");
            numberOfWeighings++;

            int[] suspectGroup = groups[suspectGroupIndex];

            String coinPositionName;

            if (suspectGroup[0] == suspectGroup[1]) { // The fake coin is in the third position
                coinPositionName = "third";
            } else if (suspectGroup[0] == suspectGroup[2]) { // The fake coin is in the second position
                coinPositionName = "second";
            } else { // The fake coin is in the first position
                coinPositionName = "first";
            }

            String groupPositionName = switch (suspectGroupIndex) {
                case 0 -> "first";
                case 1 -> "second";
                default -> "third";
            };

            LOGGER.info("The fake coin is the {} coin in the {} group.", coinPositionName, groupPositionName);
        }

        LOGGER.info("Total number of weighings: {}", numberOfWeighings);
    }

    /**
     * Enum to represent the groups of coins.
     */
    private enum Group {
        FIRST(0), SECOND(1), THIRD(2);

        private final int index;

        Group(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }
    }
}