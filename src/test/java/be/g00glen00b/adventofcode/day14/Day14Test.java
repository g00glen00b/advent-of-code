package be.g00glen00b.adventofcode.day14;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.IntStream;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.splitLinesByEmptyLine;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day14Test {
    @ParameterizedTest
    @CsvSource({
        "/day14_sample.txt,1,7",
        "/day14_sample.txt,2,13",
        "/day14_sample.txt,3,25",
        "/day14_sample.txt,4,49",
        "/day14_sample.txt,5,97",
        "/day14_sample.txt,10,3073"
    })
    void partOne_count(String fileLocation, int iterations, int expectedNumberOfElements) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> parts = splitLinesByEmptyLine(lines);
        PolymerTemplate template = PolymerTemplate.fromLine(parts.get(0).get(0));
        PolymerPairInsertionRules rules = PolymerPairInsertionRules.fromLines(parts.get(1));
        PolymerTemplate result = IntStream
            .range(0, iterations)
            .boxed()
            .reduce(template, (temporaryResult, iteration) -> temporaryResult.applyRules(rules), unsupported());
        assertThat(result.calculateSize()).isEqualTo(expectedNumberOfElements);
    }

    @ParameterizedTest
    @CsvSource({
        "/day14_sample.txt,10,1588",
        "/day14_full.txt,10,2967"
    })
    void partOne_elementCount(String fileLocation, int iterations, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> parts = splitLinesByEmptyLine(lines);
        PolymerTemplate template = PolymerTemplate.fromLine(parts.get(0).get(0));
        PolymerPairInsertionRules rules = PolymerPairInsertionRules.fromLines(parts.get(1));
        PolymerTemplate result = IntStream
            .range(0, iterations)
            .boxed()
            .reduce(template, (temporaryResult, iteration) -> temporaryResult.applyRules(rules), unsupported());
        Map<Character, Long> resultCounts = result.calculateElementCounts();
        LongSummaryStatistics statistics = resultCounts
            .values()
            .stream()
            .mapToLong(Long::longValue)
            .summaryStatistics();
        assertThat(statistics.getMax() - statistics.getMin()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day14_sample.txt,40,2188189693529",
        "/day14_full.txt,40,3692219987038"
    })
    void partTwo(String fileLocation, int iterations, long expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> parts = splitLinesByEmptyLine(lines);
        PolymerTemplate template = PolymerTemplate.fromLine(parts.get(0).get(0));
        PolymerPairInsertionRules rules = PolymerPairInsertionRules.fromLines(parts.get(1));
        PolymerTemplate result = IntStream
            .range(0, iterations)
            .boxed()
            .reduce(template, (temporaryResult, iteration) -> temporaryResult.applyRules(rules), unsupported());
        Map<Character, Long> resultCounts = result.calculateElementCounts();
        LongSummaryStatistics statistics = resultCounts
            .values()
            .stream()
            .mapToLong(Long::longValue)
            .summaryStatistics();
        assertThat(statistics.getMax() - statistics.getMin()).isEqualTo(expectedOutput);
    }
}
