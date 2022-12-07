package codes.dimitri.adventofcode.day7;

import java.util.Optional;

public record FileOutputCommand(String name, int size) implements Command {
    public static Optional<FileOutputCommand> fromArguments(String... arguments) {
        if (arguments.length != 2) return Optional.empty();
        try {
            int size = Integer.parseInt(arguments[0]);
            return Optional.of(new FileOutputCommand(arguments[1], size));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
