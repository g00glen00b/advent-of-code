package be.g00glen00b.adventofcode.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.calculateAverage;
import static be.g00glen00b.adventofcode.TestUtils.calculateMedian;
import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.streamNumbers;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Day7Test {
    @ParameterizedTest
    @CsvSource({
        "/day7_sample.txt,2,37",
        "/day7_full.txt,345,343605"
    })
    void part1(String fileLocation, int expectedPosition, int expectedOutput) throws URISyntaxException, IOException {
        String line = readLines(fileLocation).iterator().next();
        List<Integer> numbers = streamNumbers(line).collect(toList());
        int median = (int) calculateMedian(numbers);
        int fuel = calculateTotalLinearDistance(numbers, median);
        assertThat(median).isEqualTo(expectedPosition);
        assertThat(fuel).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day7_sample.txt,168",
        "/day7_full.txt,96744904"
    })
    void part2(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        String line = readLines(fileLocation).iterator().next();
        List<Integer> numbers = streamNumbers(line).collect(toList());
        double average = calculateAverage(numbers);
        int floorAverage = (int) average;
        int ceilAverage = (int) (average + 0.5d);
        int fuelFloor = calculateTotalGaussDistance(numbers, floorAverage);
        int fuelCeil = calculateTotalGaussDistance(numbers, ceilAverage);
        int fuel = Math.min(fuelFloor, fuelCeil);
        assertThat(fuel).isEqualTo(expectedOutput);
    }

    private int calculateTotalGaussDistance(List<Integer> numbers, int target) {
        return numbers
            .stream()
            .mapToInt(Integer::intValue)
            .map(position -> Math.abs(position - target))
            .map(distance -> distance * (distance + 1) / 2)
            .sum();
    }

    private int calculateTotalLinearDistance(List<Integer> numbers, int target) {
        return numbers
            .stream()
            .mapToInt(Integer::intValue)
            .map(position -> Math.abs(position - target))
            .sum();
    }
}
