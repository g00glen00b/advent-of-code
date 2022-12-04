package codes.dimitri.adventofcode.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day4Test {
    @ParameterizedTest
    @CsvSource({
        "/day4/sample.txt,2",
        "/day4/full.txt,582"
    })
    void partOne(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        long result = lines
            .stream()
            .map(AssignmentPair::ofLine)
            .filter(AssignmentPair::isFullyContained)
            .count();
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "/day4/sample.txt,4",
        "/day4/full.txt,893"
    })
    void partTwo(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        long result = lines
            .stream()
            .map(AssignmentPair::ofLine)
            .filter(AssignmentPair::isOverlapping)
            .count();
        assertThat(result).isEqualTo(expectedResult);
    }
}
