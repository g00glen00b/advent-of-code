package codes.dimitri.adventofcode.day7;

import java.util.Optional;

public record ChangeDirectoryCommand(String name) implements Command {
    public static final String CHANGE_DIRECTORY_COMMAND = "cd";

    public static Optional<ChangeDirectoryCommand> fromArguments(String... arguments) {
        if (arguments.length != 3) return Optional.empty();
        if (!COMMAND_BANG.equals(arguments[0])) return Optional.empty();
        if (!CHANGE_DIRECTORY_COMMAND.equals(arguments[1])) return Optional.empty();
        return Optional.of(new ChangeDirectoryCommand(arguments[2]));
    }
}
