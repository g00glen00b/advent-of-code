package codes.dimitri.adventofcode.day7;

import java.util.Optional;

public record DirectoryOutputCommand(String name) implements Command {
    private static final String DIRECTORY_OUTPUT = "dir";
    public static Optional<DirectoryOutputCommand> fromArguments(String... arguments) {
        if (arguments.length != 2) return Optional.empty();
        if (!DIRECTORY_OUTPUT.equals(arguments[0])) return Optional.empty();
        return Optional.of(new DirectoryOutputCommand(arguments[1]));
    }
}
