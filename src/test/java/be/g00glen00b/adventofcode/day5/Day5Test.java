package be.g00glen00b.adventofcode.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {
    @ParameterizedTest
    @CsvSource({
        "/day5_part1_sample.txt,5",
        "/day5_part1_full.txt,5698"
    })
    void part1(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Plot plot = lines
            .stream()
            .map(line -> Vent.fromLine(false, line))
            .reduce(Plot.empty(), Plot::withVent, unsupported());
        assertThat(plot.calculateOverlaps()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day5_part1_sample.txt,12",
        "/day5_part1_full.txt,15463"
    })
    void part2(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        Plot plot = lines
            .stream()
            .map(line -> Vent.fromLine(true, line))
            .reduce(Plot.empty(), Plot::withVent, unsupported());
        assertThat(plot.calculateOverlaps()).isEqualTo(expectedOutput);
    }
}
