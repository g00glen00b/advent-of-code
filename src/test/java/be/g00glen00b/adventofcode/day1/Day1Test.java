package be.g00glen00b.adventofcode.day1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {
    @ParameterizedTest
    @CsvSource({
        "/day1_part1_sample.txt,7",
        "/day1_part1_full.txt,1448"
    })
    void part1(String fileLocation, int expectedIncreases) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        DepthMeasurement result = lines
            .stream()
            .map(Integer::parseInt)
            .reduce(DepthMeasurement.initial(), DepthMeasurement::withDepth, unsupported());
        assertThat(result.getNumberOfIncreases()).isEqualTo(expectedIncreases);
    }

    @ParameterizedTest
    @CsvSource({
        "/day1_part1_sample.txt,5",
        "/day1_part1_full.txt,1471"
    })
    void part2(String fileLocation, int expectedIncreases) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        WindowMeasurement result = lines
            .stream()
            .map(Integer::parseInt)
            .reduce(WindowMeasurement.initial(3), WindowMeasurement::withDepth, unsupported());
        assertThat(result.getNumberOfIncreases()).isEqualTo(expectedIncreases);
    }

}
