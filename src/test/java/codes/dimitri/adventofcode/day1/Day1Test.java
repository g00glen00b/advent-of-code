package codes.dimitri.adventofcode.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {
    @ParameterizedTest
    @CsvSource({
        "/day1/sample.txt,24000",
        "/day1/full.txt,73211"
    })
    void partOne(String fileLocation, int expectedCalories) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Elves elves = Elves.empty();
        lines.forEach(elves::addInput);
        assertThat(elves.calculateMostCaloriesByElf()).isEqualTo(expectedCalories);
    }

    @ParameterizedTest
    @CsvSource({
        "/day1/sample.txt,45000",
        "/day1/full.txt,213958"
    })
    void partTwo(String fileLocation, int expectedCalories) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Elves elves = Elves.empty();
        lines.forEach(elves::addInput);
        assertThat(elves.calculateMostCaloriesByTopThreeElves()).isEqualTo(expectedCalories);
    }
}
