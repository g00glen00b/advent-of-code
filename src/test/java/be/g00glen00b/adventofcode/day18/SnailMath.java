package be.g00glen00b.adventofcode.day18;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SnailMath {
    private static final Pattern PAIR_PARSE_PATTERN = Pattern.compile("(\\[|]|\\d+|,)");
    private static final Pattern NUMERIC_PAIR_PATTERN = Pattern.compile("(\\[(\\d+),(\\d+)])");
    private static final List<String> NON_NUMBERS = List.of("[", "]", ",");

    public static String add(String left, String right) {
        return reduce("[" + left + "," + right + "]");
    }

    public static String reduce(String input) {
        List<String> values = parse(input);
        if (reduceExplode(values)) return reduce(String.join("", values));
        else if (reduceSplit(values)) return reduce(String.join("", values));
        else return input;
    }

    public static String calculateMagnitude(String input) {
        Matcher matcher = NUMERIC_PAIR_PATTERN.matcher(input);
        String result = matcher.replaceAll(match -> Integer.toString(3 * Integer.parseInt(match.group(2)) + 2 * Integer.parseInt(match.group(3))));
        return input.equals(result) ? input : calculateMagnitude(result);
    }

    public static int calculateMagnitudeNumeric(String input) {
        return Integer.parseInt(calculateMagnitude(input));
    }

    private static boolean reduceSplit(List<String> values) {
        for (int index = 0; index < values.size(); index++) {
            String value = values.get(index);
            if (isNumericValue(value)) {
                int currentValue = Integer.parseInt(value);
                if (currentValue > 9) {
                    int left = Math.floorDiv(currentValue, 2);
                    int right = currentValue - left;
                    values.set(index, "[");
                    values.add(index + 1, Integer.toString(left));
                    values.add(index + 2, ",");
                    values.add(index + 3, Integer.toString(right));
                    values.add(index + 4, "]");
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean reduceExplode(List<String> values) {
        int pairLevel = 0;
        for (int index = 0; index < values.size(); index++)  {
            String value = values.get(index);
            if ("[".equals(value)) pairLevel++;
            else if ("]".equals(value)) pairLevel--;
            if (isNumericValue(value)) {
                int rightIndex = index + 2;
                int currentValue = Integer.parseInt(value);
                if (pairLevel > 4 && rightIndex < values.size() && isNumericValue(values.get(index + 2))) {
                    int previousNumberIndex = findPreviousNumberIndex(values, index);
                    int nextNumberIndex = findNextNumberIndex(values, rightIndex);
                    if (previousNumberIndex >= 0) {
                        int previousValue = Integer.parseInt(values.get(previousNumberIndex));
                        values.set(previousNumberIndex, Integer.toString(previousValue + currentValue));
                    }
                    if (nextNumberIndex >= 0) {
                        int nextValue = Integer.parseInt(values.get(nextNumberIndex));
                        int rightValue = Integer.parseInt(values.get(index + 2));
                        values.set(nextNumberIndex, Integer.toString(nextValue + rightValue));
                    }
                    values.subList(index - 1, index + 4).clear();
                    values.add(index - 1, "0");
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isNumericValue(String value) {
        return !NON_NUMBERS.contains(value);
    }

    private static int findPreviousNumberIndex(List<String> values, int startingIndex) {
        for (int index = startingIndex - 1; index >= 0; index--) {
            if (isNumericValue(values.get(index))) return index;
        }
        return -1;
    }

    private static int findNextNumberIndex(List<String> values, int startingIndex) {
        for (int index = startingIndex + 1; index < values.size(); index++) {
            if (isNumericValue(values.get(index))) return index;
        }
        return -1;
    }

    private static List<String> parse(String input) {
        Matcher matcher = PAIR_PARSE_PATTERN.matcher(input);
        List<String> results = new ArrayList<>();
        while (matcher.find()) results.add(matcher.group());
        return results;
    }
}
