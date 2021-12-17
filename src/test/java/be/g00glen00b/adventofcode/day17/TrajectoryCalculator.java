package be.g00glen00b.adventofcode.day17;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class TrajectoryCalculator {
    public static Probe calculateHighestHit(TargetArea area) {
        return streamAllHits(area, 1, 1000)
            .max(comparingInt(Probe::getHighestY))
            .orElseThrow();
    }

    public static List<Probe> calculateAllHits(TargetArea area) {
        return streamAllHits(area, -1000, 1000)
            .collect(toList());
    }

    private static Stream<Probe> streamAllHits(TargetArea area, int minVerticalSpeed, int maxVerticalSpeed) {
        return IntStream
            .rangeClosed(1, area.getX() + area.getWidth())
            .mapToObj(horizontalSpeed -> horizontalSpeed * (area.getX() < 0 ? -1 : 1))
            .flatMap(horizontalSpeed -> IntStream
                .range(0, maxVerticalSpeed - minVerticalSpeed)
                .map(verticalSpeed -> minVerticalSpeed + verticalSpeed)
                .mapToObj(verticalSpeed -> new Probe(0, 0, 0, horizontalSpeed, verticalSpeed)))
            .map(probe -> calculateHit(area, probe))
            .flatMap(Optional::stream);
    }

    public static Optional<Probe> calculateHit(TargetArea area, Probe probe) {
        Probe result = probe;
        do {
            result = result.calculateNext();
        } while (!area.isWithinArea(result) && (!result.isFalling() || !area.isBelowArea(result)));
        if (area.isWithinArea(result)) return Optional.of(result);
        else return Optional.empty();
    }
}
