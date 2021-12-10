package be.g00glen00b.adventofcode.day6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.IntStream;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day6Test {
    @ParameterizedTest
    @CsvSource({
        "/day6_part1_sample.txt,18,26",
        "/day6_part1_sample.txt,80,5934",
        "/day6_part1_full.txt,80,362666",
        "/day6_part1_sample.txt,256,26984457539",
        "/day6_part1_full.txt,256,1640526601595"
    })
    void part1(String fileLocation, int duration, long expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        String ages = lines.get(0);
        LanternfishPopulation population = IntStream
            .rangeClosed(1, duration)
            .boxed()
            .reduce(LanternfishPopulation.fromLine(ages), (result, day) -> result.nextDay(), unsupported());
        assertThat(population.calculatePopulation()).isEqualTo(expectedOutput);
    }
}
