package tech.zmario.school.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import tech.zmario.school.api.exercise.Exercise;
import tech.zmario.school.api.service.ExerciseService;
import tech.zmario.school.exercise.impl.FrogExercise;
import tech.zmario.school.exercise.impl.TrisExercise;

import java.util.*;

/**
 * Basic implementation of the {@link ExerciseService}.
 */
public class ExerciseServiceImpl implements ExerciseService {

    private static final Logger LOGGER = LogManager.getLogger(ExerciseServiceImpl.class);
    private static final List<Exercise> DEFAULT_EXERCISES = List.of(
            new FrogExercise(),
            new TrisExercise()
    );

    private final Map<String, Exercise> exercises = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ExerciseServiceImpl() {
        for (Exercise DEFAULT_EXERCISE : DEFAULT_EXERCISES) {
            if (!register(DEFAULT_EXERCISE))
                LOGGER.warn(() -> "Failed to register default exercise: " + DEFAULT_EXERCISE.name());
        }
    }

    @Override
    public boolean register(@NotNull Exercise exercise) {
        return exercises.computeIfAbsent(exercise.name(), name -> exercise).equals(exercise);
    }

    @Override
    public boolean unregister(@NotNull Exercise exercise) {
        return unregister(exercise.name());
    }

    @Override
    public boolean unregister(@NotNull String name) {
        return exercises.remove(name) != null;
    }

    @Override
    public @NotNull Optional<Exercise> exercise(@NotNull String name) {
        return Optional.ofNullable(exercises.get(name));
    }

    @Override
    public @NotNull Collection<Exercise> exercises() {
        return Collections.unmodifiableCollection(exercises.values());
    }
}
