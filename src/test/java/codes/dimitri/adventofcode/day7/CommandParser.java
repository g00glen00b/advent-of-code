package codes.dimitri.adventofcode.day7;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CommandParser {
    private static final List<Function<String[], Optional<? extends Command>>> ACCEPTED_COMMAND_PARSERS = List.of(
        ParentDirectoryCommand::fromArguments,
        ChangeDirectoryCommand::fromArguments,
        ListingCommand::fromArguments,
        DirectoryOutputCommand::fromArguments,
        FileOutputCommand::fromArguments
    );

    public static Command parse(String line) {
        String[] arguments = line.split(" ");
        return ACCEPTED_COMMAND_PARSERS
            .stream()
            .map(parser -> parser.apply(arguments))
            .flatMap(Optional::stream)
            .findFirst()
            .orElseThrow(() -> new UnsupportedOperationException("Command not recognized: " + line));
    }

    public static DirectoryNode parse(List<String> lines) {
        DirectoryNode wrappedRoot = DirectoryNode.wrappedRoot();
        DirectoryNode cursor = wrappedRoot;
        boolean listing = false;
        for (String line : lines) {
            Command command = parse(line);
            if (command instanceof ChangeDirectoryCommand changeDirectoryCommand) {
                cursor = cursor.findDirectory(changeDirectoryCommand.name());
            } else if (command instanceof ParentDirectoryCommand) {
                cursor = cursor.getParent().orElseThrow(() -> new RuntimeException("No parent directory available"));
            } else if (command instanceof ListingCommand) {
                listing = true;
            } else if (listing && command instanceof FileOutputCommand fileOutputCommand) {
                cursor.addFileNode(fileOutputCommand.name(), fileOutputCommand.size());
            } else if (listing && command instanceof DirectoryOutputCommand directoryOutputCommand) {
                cursor.addDirectoryNode(directoryOutputCommand.name());
            } else {
                throw new UnsupportedOperationException("Command not recognized: " + line);
            }
        }
        return wrappedRoot.findDirectory("/");
    }
}
