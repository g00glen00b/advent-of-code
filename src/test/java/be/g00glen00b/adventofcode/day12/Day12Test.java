package be.g00glen00b.adventofcode.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day12Test {
    @ParameterizedTest
    @CsvSource({
        "/day12_part1_sample1.txt,10",
        "/day12_part1_sample2.txt,19",
        "/day12_part1_sample3.txt,226",
        "/day12_part1_full.txt,5333"
    })
    public void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        CaveSystem system = CaveSystem.fromLines(lines);
        List<CaveRoute> routes = system.calculateRoutes();
        assertThat(routes.size()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day12_part1_sample1.txt,36",
        "/day12_part1_sample2.txt,103",
        "/day12_part1_sample3.txt,3509",
        "/day12_part1_full.txt,146553"
    })
    public void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        CaveSystem system = CaveSystem.fromLines(lines);
        List<CaveRoute> routes = system.calculateSpecialRoutes();
        assertThat(routes.size()).isEqualTo(expectedOutput);
    }
}
