package be.g00glen00b.adventofcode.day12;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
public class SpecialCaveRoute {
    List<Cave> visitedCaves;
    List<Cave> visitedSmallCaves;
    Cave importantSmallCave;

    private SpecialCaveRoute(List<Cave> visitedCaves, List<Cave> visitedSmallCaves, Cave importantSmallCave) {
        this.visitedCaves = List.copyOf(visitedCaves);
        this.visitedSmallCaves = List.copyOf(visitedSmallCaves);
        this.importantSmallCave = importantSmallCave;
    }

    public static SpecialCaveRoute start() {
        return new SpecialCaveRoute(List.of(Cave.start()), List.of(Cave.start()), null);
    }

    public List<SpecialCaveRoute> visit(Cave cave) {
        if (!cave.isSmallCave()) {
            List<Cave> copyOfVisitedCaves = new ArrayList<>(visitedCaves);
            copyOfVisitedCaves.add(cave);
            return List.of(new SpecialCaveRoute(copyOfVisitedCaves, visitedSmallCaves, importantSmallCave));
        } else if (visitedSmallCaves.contains(cave) && (importantSmallCave != null || cave.isStart())) {
            return List.of();
        } else {
            List<Cave> copyOfVisitedCaves = new ArrayList<>(visitedCaves);
            List<Cave> copyOfVisitedSmallCaves = new ArrayList<>(visitedSmallCaves);
            copyOfVisitedCaves.add(cave);
            copyOfVisitedSmallCaves.add(cave);
            if (visitedSmallCaves.contains(cave)) {
                return List.of(new SpecialCaveRoute(copyOfVisitedCaves, copyOfVisitedSmallCaves, cave));
            } else {
                return List.of(
                    new SpecialCaveRoute(copyOfVisitedCaves, copyOfVisitedSmallCaves, cave),
                    new SpecialCaveRoute(copyOfVisitedCaves, copyOfVisitedSmallCaves, importantSmallCave)
                );
            }
        }
    }

    public Cave getCurrentCave() {
        return visitedCaves.get(visitedCaves.size() - 1);
    }

    public CaveRoute toCaveRoute() {
        return new CaveRoute(visitedCaves, visitedSmallCaves);
    }
}
