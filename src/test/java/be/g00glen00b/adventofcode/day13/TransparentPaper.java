package be.g00glen00b.adventofcode.day13;

import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Value
public class TransparentPaper {
    List<Coordinate> marks;
    int width;
    int height;

    public TransparentPaper fold(FoldInstruction instruction) {
        List<Coordinate> newMarks = marks
            .stream()
            .map(coordinate -> calculateCoordinateAfterFold(coordinate, instruction))
            .flatMap(Optional::stream)
            .distinct()
            .collect(toList());
        int newWidth = instruction.isXType() ? instruction.getValue() : width;
        int newHeight = instruction.isYType() ? instruction.getValue() : height;
        return new TransparentPaper(newMarks, newWidth, newHeight);
    }

    private Optional<Coordinate> calculateCoordinateAfterFold(Coordinate coordinate, FoldInstruction instruction) {
        if (instruction.isXType()) {
            int distanceFromLine = Math.abs(instruction.getValue() - coordinate.getX());
            if (distanceFromLine > 0) return Optional.of(coordinate.withX(instruction.getValue() - distanceFromLine));
            else return Optional.empty();
        } else if (instruction.isYType()) {
            int distanceFromLine = Math.abs(instruction.getValue() - coordinate.getY());
            if (distanceFromLine > 0) return Optional.of(coordinate.withY(instruction.getValue() - distanceFromLine));
            else return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    public int calculateMarkCount() {
        return marks.size();
    }

    private List<Coordinate> calculateAllCoordinates() {
        return IntStream
            .range(0, height)
            .boxed()
            .flatMap(y -> IntStream
                .range(0, width)
                .mapToObj(x -> new Coordinate(x, y)))
            .collect(toList());
    }

    public void print() {
        IntStream
            .range(0, height)
            .sorted()
            .mapToObj(y -> IntStream
                .range(0, width)
                .sorted()
                .mapToObj(x -> new Coordinate(x, y))
                .map(coordinate -> marks.contains(coordinate) ? "#" : " ")
                .collect(Collectors.joining()))
            .forEach(System.out::println);
    }

    public static TransparentPaper fromLines(List<String> lines) {
        List<Coordinate> marks = lines
            .stream()
            .map(Coordinate::fromLine)
            .collect(toList());
        return new TransparentPaper(marks, calculateWidth(marks), calculateHeight(marks));
    }

    private static int calculateWidth(List<Coordinate> marks) {
        return marks
            .stream()
            .mapToInt(Coordinate::getX)
            .max()
            .orElse(0) + 1;
    }

    private static int calculateHeight(List<Coordinate> marks) {
        return marks
            .stream()
            .mapToInt(Coordinate::getY)
            .max()
            .orElse(0) + 1;
    }
}
