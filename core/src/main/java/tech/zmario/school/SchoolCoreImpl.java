package tech.zmario.school;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.zmario.school.api.SchoolCore;
import tech.zmario.school.api.service.CommandService;
import tech.zmario.school.api.service.ExerciseService;
import tech.zmario.school.service.CommandServiceImpl;
import tech.zmario.school.service.ExerciseServiceImpl;

/**
 * Main class of the application.
 */
public class SchoolCoreImpl implements SchoolCore {

    /**
     * Logger for the application.
     */
    private static final Logger LOGGER = LogManager.getLogger(SchoolCoreImpl.class);

    /**
     * The {@link ExerciseService} of the application. Default implementation is {@link ExerciseServiceImpl}.
     */
    private final ExerciseService exerciseService;
    /**
     * The {@link CommandService} of the application. Default implementation is {@link CommandServiceImpl}.
     */
    private final CommandService commandService;

    /**
     * Creates a new instance of the application.
     */
    public SchoolCoreImpl() {
        this.exerciseService = new ExerciseServiceImpl();

        LOGGER.info(() -> "Application successfully started. Listening for commands.");
        this.commandService = new CommandServiceImpl(this);

        commandService.start();
    }

    @Override
    public ExerciseService exerciseService() {
        return exerciseService;
    }

    @Override
    public CommandService commandService() {
        return commandService;
    }
}
