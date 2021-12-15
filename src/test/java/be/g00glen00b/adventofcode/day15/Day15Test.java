package be.g00glen00b.adventofcode.day15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day15Test {
    @ParameterizedTest
    @CsvSource({
        "/day15_sample.txt,40",
        "/day15_full.txt,595"
    })
    public void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        SmallCave cave = SmallCave.fromLines(lines);
        Coordinates topLeft = cave.calculateTopLeftCoordinates();
        Coordinates bottomRight = cave.calculateBottomRightCoordinates();
        int totalRisk = cave.calculateTotalDistance(topLeft, bottomRight);
        assertThat(totalRisk).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day15_sample.txt,315",
        "/day15_full.txt,2914"
    })
    public void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        SmallCave cave = SmallCave.fromLines(lines).grow();
        Coordinates topLeft = cave.calculateTopLeftCoordinates();
        Coordinates bottomRight = cave.calculateBottomRightCoordinates();
        int totalRisk = cave.calculateTotalDistance(topLeft, bottomRight);
        assertThat(totalRisk).isEqualTo(expectedOutput);
    }
}
