package be.g00glen00b.adventofcode.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;

public class Day9Test {
    @ParameterizedTest
    @CsvSource({
        "/day9_part1_sample.txt,15",
        "/day9_part1_full.txt,452"
    })
    public void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Area area = Area.fromLines(lines);
        assertThat(area.calculateRisk()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day9_part1_sample.txt,1134",
        "/day9_part1_full.txt,1263735"
    })
    public void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Area area = Area.fromLines(lines);
        List<Basin> basins = area.calculateBasins();
        int result = basins
            .stream()
            .sorted(comparingInt(Basin::size).reversed())
            .limit(3)
            .mapToInt(Basin::size)
            .reduce(1, (temporaryResult, size) -> temporaryResult * size);
        assertThat(result).isEqualTo(expectedOutput);
    }
}
