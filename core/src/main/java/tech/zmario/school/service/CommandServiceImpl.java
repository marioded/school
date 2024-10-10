package tech.zmario.school.service;

import tech.zmario.school.api.SchoolCore;
import tech.zmario.school.api.command.Command;
import tech.zmario.school.api.service.CommandService;
import tech.zmario.school.command.impl.ExerciseCommand;
import tech.zmario.school.command.impl.HelpCommand;

import java.util.*;

/**
 * Service for commands. This service is responsible for registering and unregistering commands.
 */
public class CommandServiceImpl implements CommandService {

    private final SchoolCore schoolCore;
    private final Map<String, Command> commands = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Creates a new command service with the given core.
     *
     * @param schoolCore Core of the school application.
     */
    public CommandServiceImpl(SchoolCore schoolCore) {
        this.schoolCore = schoolCore;
    }

    @Override
    public void start() {
        register(new ExerciseCommand(schoolCore));
        register(new HelpCommand(schoolCore));

        Scanner scanner = new Scanner(System.in);

        showHelpPage(scanner);
    }

    @Override
    public boolean register(Command command) {
        return commands.computeIfAbsent(command.name(), name -> command).equals(command);
    }

    @Override
    public boolean unregister(Command command) {
        return unregister(command.name());
    }

    @Override
    public boolean unregister(String name) {
        return commands.remove(name) != null;
    }

    @Override
    public Optional<Command> command(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    @Override
    public Collection<Command> commands() {
        return Collections.unmodifiableCollection(commands.values());
    }

    @Override
    public void showHelpPage(Scanner scanner) {
        command("help").ifPresent(command -> command.execute(scanner));
    }
}
