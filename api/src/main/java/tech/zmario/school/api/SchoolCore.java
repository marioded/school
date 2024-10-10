package tech.zmario.school.api;

import tech.zmario.school.api.service.CommandService;
import tech.zmario.school.api.service.ExerciseService;

/**
 * Represents the core of the school application.
 */
public interface SchoolCore {

    /**
     * Retrieves the {@link ExerciseService} of the application.
     *
     * @return The {@link ExerciseService} of the application.
     */
    ExerciseService exerciseService();

    /**
     * Retrieves the {@link CommandService} of the application.
     *
     * @return The {@link CommandService} of the application.
     */
    CommandService commandService();

}
