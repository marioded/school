package tech.zmario.school.api.service;

import org.jetbrains.annotations.NotNull;
import tech.zmario.school.api.exercise.Exercise;

import java.util.Collection;
import java.util.Optional;

/**
 * Service for exercises. This service is responsible for registering and unregistering exercises.
 */
public interface ExerciseService {

    /**
     * Registers an exercise.
     *
     * @param exercise Exercise to register.
     * @return <code>true</code> if the exercise was successfully registered, <code>false</code> otherwise.
     */
    boolean register(@NotNull Exercise exercise);

    /**
     * Unregisters an exercise.
     *
     * @param exercise Exercise to unregister.
     * @return <code>true</code> if the exercise was successfully unregistered, <code>false</code> otherwise.
     */
    boolean unregister(@NotNull Exercise exercise);

    /**
     * Unregisters an exercise by its name.
     *
     * @param name Name of the exercise to unregister.
     * @return <code>true</code> if the exercise was successfully unregistered, <code>false</code> otherwise.
     */
    boolean unregister(@NotNull String name);

    /**
     * Retrieves an exercise by its name.
     *
     * @param name Name of the exercise.
     * @return The exercise with the given name, or <code>null</code> if no such exercise exists.
     */
    @NotNull Optional<Exercise> exercise(@NotNull String name);

    /**
     * Retrieves all registered exercises as an unmodifiable collection.
     *
     * @return All registered exercises.
     */
    @NotNull Collection<Exercise> exercises();

}
