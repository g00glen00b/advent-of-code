package codes.dimitri.adventofcode.day7;

import java.util.Optional;

public record ParentDirectoryCommand() implements Command {
    private static final String PARENT_DIRECTORY = "..";

    public static Optional<ParentDirectoryCommand> fromArguments(String... arguments) {
        if (arguments.length != 3) return Optional.empty();
        if (!COMMAND_BANG.equals(arguments[0])) return Optional.empty();
        if (!ChangeDirectoryCommand.CHANGE_DIRECTORY_COMMAND.equals(arguments[1])) return Optional.empty();
        if (!PARENT_DIRECTORY.equals(arguments[2])) return Optional.empty();
        return Optional.of(new ParentDirectoryCommand());
    }
}
