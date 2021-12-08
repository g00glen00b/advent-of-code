package be.g00glen00b.adventofcode.day3;

import lombok.Value;

@Value
public class DiagnosticSummaryNode {
    int timesZero;
    int timesOne;
    DiagnosticSummaryNode childrenOfZero;
    DiagnosticSummaryNode childrenOfOne;

    public static DiagnosticSummaryNode empty() {
        return new DiagnosticSummaryNode(0, 0, null, null);
    }

    public DiagnosticSummaryNode withValue(String value) {
        char firstChar = value.charAt(0);
        int timesZero = this.timesZero;
        int timesOne = this.timesOne;
        DiagnosticSummaryNode childrenOfZero = this.childrenOfZero;
        DiagnosticSummaryNode childrenOfOne = this.childrenOfOne;
        if (firstChar == '0') timesZero++;
        if (firstChar == '1') timesOne++;
        if (value.length() > 1) {
            String valueWithoutFirstDigit = value.substring(1);
            if (childrenOfZero == null) childrenOfZero = DiagnosticSummaryNode.empty();
            if (childrenOfOne == null) childrenOfOne = DiagnosticSummaryNode.empty();
            if (firstChar == '0') childrenOfZero = childrenOfZero.withValue(valueWithoutFirstDigit);
            if (firstChar == '1') childrenOfOne = childrenOfOne.withValue(valueWithoutFirstDigit);
        }
        return new DiagnosticSummaryNode(timesZero, timesOne, childrenOfZero, childrenOfOne);
    }

    public int calculateRemainingValues() {
        return timesZero + timesOne;
    }

    public String calculateOxygenRatingString() {
        String result = "";
        if (timesOne >= timesZero) {
            result += "1";
            if (childrenOfOne != null) result += childrenOfOne.calculateOxygenRatingString();
        } else {
            result += "0";
            if (childrenOfZero != null) result += childrenOfZero.calculateOxygenRatingString();
        }
        return result;
    }

    public int calculateOxygenRating() {
        return Integer.parseInt(calculateOxygenRatingString(), 2);
    }

    public String calculateCO2RatingString() {
        String result = "";
        if ((timesZero <= timesOne && timesZero > 0) || timesOne == 0) {
            result += "0";
            if (childrenOfZero != null) result += childrenOfZero.calculateCO2RatingString();
        } else {
            result += "1";
            if (childrenOfOne != null) result += childrenOfOne.calculateCO2RatingString();
        }
        return result;
    }

    public int calculateCO2Rating() {
        return Integer.parseInt(calculateCO2RatingString(), 2);
    }

    public int calculateLifeSupportRating() {
        return calculateOxygenRating() * calculateCO2Rating();
    }
}
