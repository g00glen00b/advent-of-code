package be.g00glen00b.adventofcode.day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static be.g00glen00b.adventofcode.TestUtils.calculateMedian;
import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class Day10Test {
    @ParameterizedTest
    @CsvSource({
        "/day10_part1_sample.txt,26397",
        "/day10_part1_full.txt,318081"
    })
    public void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        int output = lines
            .stream()
            .map(ChunkDecoderResult::fromLine)
            .filter(ChunkDecoderResult::isCorrupt)
            .mapToInt(ChunkDecoderResult::calculateErrorScore)
            .sum();
        assertThat(output).isEqualTo(expectedOutput);
    }

    @Test
    void partTwoTemp() {
        ChunkDecoderResult result = ChunkDecoderResult.fromLine("[({(<(())[]>[[{[]{<()<>>");
        assertThat(result.calculateCompletionScore()).isEqualTo(288957);
    }

    @ParameterizedTest
    @CsvSource({
        "/day10_part1_sample.txt,288957",
        "/day10_part1_full.txt,4361305341"
    })
    public void partTwo(String fileLocation, long expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<Long> results = lines
            .stream()
            .map(ChunkDecoderResult::fromLine)
            .filter(not(ChunkDecoderResult::isCorrupt))
            .filter(ChunkDecoderResult::isIncomplete)
            .map(ChunkDecoderResult::calculateCompletionScore)
            .sorted()
            .collect(toList());
        assertThat((long) calculateMedian(results)).isEqualTo(expectedOutput);
    }
}
