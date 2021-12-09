package be.g00glen00b.adventofcode.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day8Test {
    @ParameterizedTest
    @CsvSource({
        "/day8_part1_sample.txt,26",
        "/day8_part1_full.txt,412"
    })
    void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<Integer> uniqueDigitPatterns = List.of(2, 3, 4, 7);
        long result = lines
            .stream()
            .map(line -> line.split(" \\| "))
            .map(parts -> parts[1])
            .map(outputLine -> outputLine.split(" "))
            .flatMap(Arrays::stream)
            .filter(outputDigit -> uniqueDigitPatterns.contains(outputDigit.length()))
            .count();
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    void partTwoPartial() {
        Display display = Display.fromInput("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab");
        int result = display.decode("cdfeb fcadb cdfeb cdbaf");
        assertThat(result).isEqualTo(5353);
    }

    @ParameterizedTest
    @CsvSource({
        "/day8_part1_sample.txt,61229",
        "/day8_part1_full.txt,978171"
    })
    void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        int result = lines
            .stream()
            .map(line -> line.split(" \\| "))
            .mapToInt(parts -> Display.fromInput(parts[0]).decode(parts[1]))
            .sum();
        assertThat(result).isEqualTo(expectedOutput);
    }
}
