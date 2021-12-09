package be.g00glen00b.adventofcode.day9;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Value
public class Basin {
    List<Coordinate> coordinates;
    List<Coordinate> edge;

    public static Basin fromLowPoint(Coordinate lowPoint) {
        return new Basin(List.of(lowPoint), List.of(lowPoint));
    }

    public int size() {
        return coordinates.size();
    }

    public Basin withEdge(List<Coordinate> edge) {
        log.info("Increasing basin with {} edge coordinates", edge.size());
        List<Coordinate> copy = new ArrayList<>(this.coordinates);
        copy.addAll(edge);
        return new Basin(copy, edge);
    }

    public boolean contains(Coordinate coordinate) {
        return coordinates.contains(coordinate);
    }
}
