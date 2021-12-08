package be.g00glen00b.adventofcode.day5;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Value
public class Plot {
    int[][] plot;

    public static Plot empty() {
        return new Plot(new int[1000][1000]);
    }

    public Plot withVent(Vent vent) {
        int[][] copy = new int[1000][1000];
        System.arraycopy(plot, 0, copy, 0, 1000);
        log.info("Adding {}", vent.toString());
        vent
            .calculateAllCoordinates()
            .forEach(coordinate -> copy[coordinate.getX()][coordinate.getY()]++);
        return new Plot(copy);
    }

    public long calculateOverlaps() {
        return Arrays
            .stream(plot)
            .flatMapToInt(Arrays::stream)
            .filter(value -> value > 1)
            .count();
    }
}
