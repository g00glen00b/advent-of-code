package be.g00glen00b.adventofcode.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test {
    @ParameterizedTest
    @CsvSource({
        "/day2_sample.txt,150",
        "/day2_full.txt,1524750"
    })
    void part1(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> commands = readLines(fileLocation);
        SubmarinePosition result = commands
            .stream()
            .map(Command::fromLine)
            .reduce(SubmarinePosition.initial(), SubmarinePosition::withCommand, unsupported());
        assertThat(result.getHorizontalPosition() * result.getDepth()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day2_sample.txt,900",
        "/day2_full.txt,1592426537"
    })
    void part2(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> commands = readLines(fileLocation);
        SubmarineAimPosition result = commands
            .stream()
            .map(Command::fromLine)
            .reduce(SubmarineAimPosition.initial(), SubmarineAimPosition::withCommand, unsupported());
        assertThat(result.getHorizontalPosition() * result.getDepth()).isEqualTo(expectedOutput);
    }
}
