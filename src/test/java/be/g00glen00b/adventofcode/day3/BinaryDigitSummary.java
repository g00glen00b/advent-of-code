package be.g00glen00b.adventofcode.day3;

import lombok.Value;

@Value
public class BinaryDigitSummary {
    int timesZero;
    int timesOne;

    public String calculateMostCommonDigit() {
        return timesZero > timesOne ? "0" : "1";
    }

    public String calculateLeastCommonDigit() {
        return timesZero > timesOne ? "1" : "0";
    }

    public static BinaryDigitSummary empty() {
        return new BinaryDigitSummary(0, 0);
    }

    public BinaryDigitSummary withValue(char value) {
        if (value == '0') {
            return new BinaryDigitSummary(timesZero + 1, timesOne);
        } else if (value == '1') {
            return new BinaryDigitSummary(timesZero, timesOne + 1);
        } else {
            return this;
        }
    }
}
