package be.g00glen00b.adventofcode.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {
    @ParameterizedTest
    @CsvSource({
        "/day3_sample.txt,5,198",
        "/day3_full.txt,12,3320834"
    })
    void part1(String fileLocation, int numberOfDigits, int expectedOutput) throws URISyntaxException, IOException {
        List<String> diagnosticReport = readLines(fileLocation);
        DiagnosticReportSummary result = diagnosticReport
            .stream()
            .reduce(DiagnosticReportSummary.empty(numberOfDigits), DiagnosticReportSummary::withValue, unsupported());
        assertThat(result.calculatePowerConsumption()).isEqualTo(expectedOutput);

    }

    @ParameterizedTest
    @CsvSource({
        "/day3_sample.txt,230",
        "/day3_full.txt,4481199"
    })
    void part2(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> diagnosticReport = readLines(fileLocation);
        DiagnosticSummaryNode result = diagnosticReport
            .stream()
            .reduce(DiagnosticSummaryNode.empty(), DiagnosticSummaryNode::withValue, unsupported());
        assertThat(result.calculateLifeSupportRating()).isEqualTo(expectedOutput);

    }
}
