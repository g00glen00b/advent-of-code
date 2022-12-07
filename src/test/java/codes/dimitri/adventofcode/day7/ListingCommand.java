package codes.dimitri.adventofcode.day7;

import java.util.Optional;

public record ListingCommand() implements Command {
    private static final String LISTING_COMMAND = "ls";

    public static Optional<ListingCommand> fromArguments(String... arguments) {
        if (arguments.length != 2) return Optional.empty();
        if (!COMMAND_BANG.equals(arguments[0])) return Optional.empty();
        if (!LISTING_COMMAND.equals(arguments[1])) return Optional.empty();
        return Optional.of(new ListingCommand());
    }
}
