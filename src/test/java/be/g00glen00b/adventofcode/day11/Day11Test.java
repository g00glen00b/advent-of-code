package be.g00glen00b.adventofcode.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.IntStream;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {
    @ParameterizedTest
    @CsvSource({
        "/day11_sample1.txt,2,9",
        "/day11_sample2.txt,1,0",
        "/day11_sample2.txt,2,35",
        "/day11_sample2.txt,10,204",
        "/day11_sample2.txt,100,1656",
        "/day11_full.txt,100,1601"
    })
    public void partOne(String fileLocation, int steps, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        OctopusField field = OctopusField.fromLines(lines);
        IntStream
            .range(0, steps)
            .forEach(step -> field.nextStep());
        assertThat(field.calculateFlashes()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "/day11_sample2.txt,195",
        "/day11_full.txt,368"
    })
    public void partTwo(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        OctopusField field = OctopusField.fromLines(lines);
        int stepWhenEveryOctopusFlashed = IntStream
            .iterate(1, step -> step + 1)
            .peek(step -> field.nextStep())
            .dropWhile(step -> !field.isAllZeroEnergyLevel())
            .limit(1)
            .findFirst()
            .orElseThrow();
        assertThat(stepWhenEveryOctopusFlashed).isEqualTo(expectedResult);
    }
}
