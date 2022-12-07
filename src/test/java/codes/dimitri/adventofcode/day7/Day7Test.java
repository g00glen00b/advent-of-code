package codes.dimitri.adventofcode.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day7Test {
    @Test
    void testChangeDirectoryCommand() {
        Optional<ChangeDirectoryCommand> result = ChangeDirectoryCommand.fromArguments("$", "cd", "/");
        assertThat(result).contains(new ChangeDirectoryCommand("/"));
    }

    @Test
    void testListingCommand() {
        Optional<ListingCommand> result = ListingCommand.fromArguments("$", "ls");
        assertThat(result).contains(new ListingCommand());
    }

    @Test
    void testDirectoryOutputCommand() {
        Optional<DirectoryOutputCommand> result = DirectoryOutputCommand.fromArguments("dir", "a");
        assertThat(result).contains(new DirectoryOutputCommand("a"));
    }

    @Test
    void testFileOutputCommand() {
        Optional<FileOutputCommand> result = FileOutputCommand.fromArguments("14848514", "b.txt");
        assertThat(result).contains(new FileOutputCommand("b.txt", 14848514));
    }

    @Test
    void testCommandParserPerLine() {
        Command changeDirectoryCommand = CommandParser.parse("$ cd /");
        Command listingCommand = CommandParser.parse("$ ls");
        Command directoryOutputCommand = CommandParser.parse("dir a");
        Command fileOutputCommand = CommandParser.parse("14848514 b.txt");
        assertThat(changeDirectoryCommand).isInstanceOf(ChangeDirectoryCommand.class);
        assertThat(listingCommand).isInstanceOf(ListingCommand.class);
        assertThat(directoryOutputCommand).isInstanceOf(DirectoryOutputCommand.class);
        assertThat(fileOutputCommand).isInstanceOf(FileOutputCommand.class);
    }

    @Test
    void testCommandParserAllLines() throws URISyntaxException, IOException {
        List<String> lines = readLines("/day7/sample.txt");
        DirectoryNode rootDirectory = CommandParser.parse(lines);
        assertThat(rootDirectory.flattenDirectories())
            .extracting(DirectoryNode::getName)
            .containsOnly("/", "a", "d", "e");
    }

    @ParameterizedTest
    @CsvSource({
        "/day7/sample.txt,100000,95437",
        "/day7/full.txt,100000,1307902"
    })
    void partOne(String fileLocation, int maximumSize, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        DirectoryNode rootDirectory = CommandParser.parse(lines);
        int sum = rootDirectory
            .flattenDirectories()
            .stream()
            .mapToInt(DirectoryNode::calculateSize)
            .filter(directorySize -> directorySize <= maximumSize)
            .sum();
        assertThat(sum).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "/day7/sample.txt,70000000,30000000,24933642",
        "/day7/full.txt,70000000,30000000,7068748"
    })
    void partTwo(String fileLocation, int totalDiskSpace, int expectedUnusedSpace, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        DirectoryNode rootDirectory = CommandParser.parse(lines);
        int currentUnusedSpace = totalDiskSpace - rootDirectory.calculateSize();
        int sum = rootDirectory
            .flattenDirectories()
            .stream()
            .mapToInt(DirectoryNode::calculateSize)
            .sorted()
            .filter(directorySize -> currentUnusedSpace + directorySize >= expectedUnusedSpace)
            .findFirst()
            .orElseThrow();
        assertThat(sum).isEqualTo(expectedResult);
    }
}
