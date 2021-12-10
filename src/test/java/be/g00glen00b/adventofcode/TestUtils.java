package be.g00glen00b.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class TestUtils {
    public static List<String> readLines(String fileLocation) throws URISyntaxException, IOException {
        URL classpathResource = Objects.requireNonNull(TestUtils.class.getResource(fileLocation));
        Path sample = Paths.get(classpathResource.toURI());
        return Files.readAllLines(sample, Charset.defaultCharset());
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
}
