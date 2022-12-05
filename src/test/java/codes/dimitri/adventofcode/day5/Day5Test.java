package codes.dimitri.adventofcode.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static codes.dimitri.adventofcode.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {
    @ParameterizedTest
    @CsvSource({
        "/day5/sample.txt,CMZ",
        "/day5/full.txt,PSNRGBTFT"
    })
    void partOne(String fileLocation, String expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> sections = splitLinesByEmptyLine(lines);
        CrateStacks stacks = CrateStacks.ofLines(sections.get(0));
        List<CrateCommand> commands = CrateCommand.ofLines(sections.get(1));
        stacks.execute9000(commands);
        List<Crate> top = stacks.findTop();
        String topString = top.stream().map(Crate::character).collect(joiningCharacters());
        assertThat(topString).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "/day5/sample.txt,MCD",
        "/day5/full.txt,BNTZFPMMW"
    })
    void partTwo(String fileLocation, String expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> sections = splitLinesByEmptyLine(lines);
        CrateStacks stacks = CrateStacks.ofLines(sections.get(0));
        List<CrateCommand> commands = CrateCommand.ofLines(sections.get(1));
        stacks.execute9001(commands);
        List<Crate> top = stacks.findTop();
        String topString = top.stream().map(Crate::character).collect(joiningCharacters());
        assertThat(topString).isEqualTo(expectedResult);
    }
}
