package be.g00glen00b.adventofcode.day3;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Value
public class DiagnosticReportSummary {
    List<BinaryDigitSummary> digitSummaries;

    public static DiagnosticReportSummary empty(int numberOfDigits) {
        List<BinaryDigitSummary> digitSummaries = IntStream
            .range(0, numberOfDigits)
            .mapToObj(nr -> BinaryDigitSummary.empty())
            .collect(toList());
        return new DiagnosticReportSummary(digitSummaries);
    }

    public DiagnosticReportSummary withValue(String value) {
        List<BinaryDigitSummary> copy = new ArrayList<>(digitSummaries);
        char[] chars = value.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            copy.set(index, copy.get(index).withValue(chars[index]));
        }
        return new DiagnosticReportSummary(List.copyOf(copy));
    }

    public int calculateGamma() {
        String value = digitSummaries
            .stream()
            .map(BinaryDigitSummary::calculateMostCommonDigit)
            .collect(joining());
        return Integer.parseInt(value, 2);
    }

    public int calculateEpsilon() {
        String value = digitSummaries
            .stream()
            .map(BinaryDigitSummary::calculateLeastCommonDigit)
            .collect(joining());
        return Integer.parseInt(value, 2);
    }

    public int calculatePowerConsumption() {
        return calculateGamma() * calculateEpsilon();
    }
}
