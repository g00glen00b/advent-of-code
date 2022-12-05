package codes.dimitri.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TestUtils {
    public static List<String> readLines(String fileLocation) throws URISyntaxException, IOException {
        URL classpathResource = Objects.requireNonNull(TestUtils.class.getResource(fileLocation));
        Path sample = Paths.get(classpathResource.toURI());
        return Files.readAllLines(sample, Charset.defaultCharset());
    }

    public static <T> List<List<T>> splitByNumber(List<T> list, int numberOfElements) {
        List<List<T>> results = new ArrayList<>();
        List<T> temporyResults = new ArrayList<>();
        for (int index = 0; index < list.size(); index++) {
            temporyResults.add(list.get(index));
            if ((index + 1) % numberOfElements == 0) {
                results.add(temporyResults);
                temporyResults = new ArrayList<>();
            }
        }
        return results;
    }

    public static <T> BinaryOperator<T> unsupported() {
        return (a, b) -> {throw new UnsupportedOperationException();};
    }

    public static Stream<Integer> streamNumbers(String line) {
        return Arrays
            .stream(line.split(","))
            .map(Integer::parseInt);
    }

    public static double calculateMedian(List<? extends Number> numbers) {
        return numbers
            .stream()
            .mapToLong(Number::longValue)
            .sorted()
            .skip((numbers.size() - 1) / 2)
            .limit(2 - numbers.size() % 2)
            .average()
            .orElse(Double.NaN);
    }

    public static double calculateAverage(List<? extends Number> numbers) {
        return numbers
            .stream()
            .mapToLong(Number::longValue)
            .average()
            .orElse(Double.NaN);
    }

    public static List<List<String>> splitLinesByEmptyLine(List<String> lines) {
        List<List<String>> results = new ArrayList<>();
        List<String> partialResults = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank() && !partialResults.isEmpty()) {
                results.add(partialResults);
                partialResults = new ArrayList<>();
            } else if (!line.isBlank()) {
                partialResults.add(line);
            }
        }
        if (!partialResults.isEmpty()) results.add(partialResults);
        return results;
    }

    public static Collector<Character, StringBuilder, String> joiningCharacters() {
        return Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString
        );
    }
}
