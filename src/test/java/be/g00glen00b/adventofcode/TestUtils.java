package be.g00glen00b.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class TestUtils {
    public static List<String> readLines(String fileLocation) throws URISyntaxException, IOException {
        URL classpathResource = Objects.requireNonNull(TestUtils.class.getResource(fileLocation));
        Path sample = Paths.get(classpathResource.toURI());
        return Files.readAllLines(sample, Charset.defaultCharset());
    }

    public static <T> BinaryOperator<T> unsupported() {
        return (a, b) -> {throw new UnsupportedOperationException();};
    }
}
