package be.g00glen00b.adventofcode.day5;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Value
public class Vent {
    boolean allowDiagonal;
    Coordinate start;
    Coordinate end;

    public static Vent fromLine(boolean allowDiagonal, String line) {
        int[] partialCoordinates = Arrays.stream(line
                .replaceAll(" -> ", ",")
                .split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        return new Vent(
            allowDiagonal,
            new Coordinate(partialCoordinates[0], partialCoordinates[1]),
            new Coordinate(partialCoordinates[2], partialCoordinates[3])
        );
    }

    public boolean isHorizontal() {
        return start.getY() == end.getY();
    }

    public boolean isVertical() {
        return start.getX() == end.getX();
    }

    public boolean isDiagonal() {
        return Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY());
    }

    public int getLength() {
        if (isHorizontal() || (isDiagonal() && allowDiagonal)) return Math.abs(start.getX() - end.getX());
        else if (isVertical()) return Math.abs(start.getY() - end.getY());
        else return -1;
    }

    public int getXDirection() {
        return Integer.compare(end.getX(), start.getX());
    }

    public int getYDirection() {
        return Integer.compare(end.getY(), start.getY());
    }

    public List<Coordinate> calculateAllCoordinates() {
        return IntStream
            .rangeClosed(0, getLength())
            .mapToObj(difference -> new Coordinate(
                start.getX() + (difference * getXDirection()),
                start.getY() + (difference * getYDirection())
            ))
            .collect(toList());
    }
}
