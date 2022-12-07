package codes.dimitri.adventofcode.day7;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CommandParser {
    private static final List<Function<String[], Optional<? extends Command>>> ACCEPTED_COMMAND_PARSERS = List.of(
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
        DirectoryNode root = DirectoryNode.root();
        DirectoryNode cursor = null;
        boolean listing = false;
        for (String line : lines) {
            Command command = parse(line);
            if (command instanceof ChangeDirectoryCommand changeDirectoryCommand && cursor == null && root.getName().equals(changeDirectoryCommand.name())) {
                cursor = root;
            } else if (command instanceof ChangeDirectoryCommand changeDirectoryCommand && cursor != null && changeDirectoryCommand.isParentDirectory()) {
                cursor = cursor.getParent().orElseThrow(() -> new RuntimeException("No parent directory available"));
            } else if (command instanceof ChangeDirectoryCommand changeDirectoryCommand && cursor != null) {
                cursor = cursor.findDirectory(changeDirectoryCommand.name());
            } else if (command instanceof ListingCommand) {
                listing = true;
            } else if (command instanceof FileOutputCommand fileOutputCommand && listing && cursor != null) {
                cursor.addFileNode(fileOutputCommand.name(), fileOutputCommand.size());
            } else if (command instanceof DirectoryOutputCommand directoryOutputCommand && listing && cursor != null) {
                cursor.addDirectoryNode(directoryOutputCommand.name());
            } else {
                throw new UnsupportedOperationException("Command not recognized: " + line);
            }
        }
        return root;
    }
}
