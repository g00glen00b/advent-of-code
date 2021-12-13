package be.g00glen00b.adventofcode.day12;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
public class CaveRoute {
    List<Cave> visitedCaves;
    List<Cave> visitedSmallCaves;

    public CaveRoute(List<Cave> visitedCaves, List<Cave> visitedSmallCaves) {
        this.visitedCaves = List.copyOf(visitedCaves);
        this.visitedSmallCaves = List.copyOf(visitedSmallCaves);
    }

    public static CaveRoute start() {
        return new CaveRoute(List.of(Cave.start()), List.of(Cave.start()));
    }

    public Optional<CaveRoute> visit(Cave cave) {
        if (visitedSmallCaves.contains(cave)) {
            return Optional.empty();
        } else {
            List<Cave> copyOfVisitedCaves = new ArrayList<>(visitedCaves);
            List<Cave> copyOfVisitedSmallCaves = new ArrayList<>(visitedSmallCaves);
            copyOfVisitedCaves.add(cave);
            if (cave.isSmallCave()) copyOfVisitedSmallCaves.add(cave);
            return Optional.of(new CaveRoute(copyOfVisitedCaves, copyOfVisitedSmallCaves));
        }
    }

    public Cave getCurrentCave() {
        return visitedCaves.get(visitedCaves.size() - 1);
    }
}
