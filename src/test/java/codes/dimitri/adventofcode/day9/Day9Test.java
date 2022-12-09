package codes.dimitri.adventofcode.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day9Test {
    @ParameterizedTest
    @CsvSource({
        "/day9/sample.txt,13",
        "/day9/full.txt,6067"
    })
    void partOne(String fileLocation, int expectedTailLocations) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Rope rope = Rope.initial(2);
        lines
            .stream()
            .map(Movement::fromLine)
            .flatMap(Collection::stream)
            .forEach(rope::move);
        assertThat(rope.getTailPositions().size()).isEqualTo(expectedTailLocations);
    }

    @ParameterizedTest
    @CsvSource({
        "/day9/sample.txt,1",
        "/day9/sample2.txt,36",
        "/day9/full.txt,2471"
    })
    void partTwo(String fileLocation, int expectedTailLocations) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Rope rope = Rope.initial(10);
        lines
            .stream()
            .map(Movement::fromLine)
            .flatMap(Collection::stream)
            .forEach(rope::move);
        assertThat(rope.getTailPositions().size()).isEqualTo(expectedTailLocations);
    }
}
