package be.g00glen00b.adventofcode.day12;

import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

@Value
public class CaveSystem {
    List<CaveLink> links;

    public static CaveSystem fromLines(List<String> lines) {
        List<CaveLink> links = lines
            .stream()
            .map(CaveLink::fromLine)
            .collect(toList());
        return new CaveSystem(links);
    }

    public List<CaveRoute> calculateRoutes() {
        return calculateRoutes(CaveRoute.start());
    }

    public List<CaveRoute> calculateRoutes(CaveRoute route) {
        Cave currentCave = route.getCurrentCave();
        if (currentCave.isEnd()) {
            return List.of(route);
        } else {
            return links
                .stream()
                .filter(link -> link.isContaining(currentCave))
                .map(CaveLink::getCaves)
                .flatMap(Collection::stream)
                .filter(not(currentCave::equals))
                .map(route::visit)
                .flatMap(Optional::stream)
                .map(this::calculateRoutes)
                .flatMap(Collection::stream)
                .collect(toList());
        }
    }

    public List<CaveRoute> calculateSpecialRoutes() {
        return calculateSpecialRoutes(SpecialCaveRoute.start())
            .stream()
            .map(SpecialCaveRoute::toCaveRoute)
            .distinct()
            .collect(toList());
    }

    public List<SpecialCaveRoute> calculateSpecialRoutes(SpecialCaveRoute route) {
        Cave currentCave = route.getCurrentCave();
        if (currentCave.isEnd()) {
            return List.of(route);
        } else {
            return links
                .stream()
                .filter(link -> link.isContaining(currentCave))
                .map(CaveLink::getCaves)
                .flatMap(Collection::stream)
                .filter(not(currentCave::equals))
                .map(route::visit)
                .flatMap(Collection::stream)
                .map(this::calculateSpecialRoutes)
                .flatMap(Collection::stream)
                .collect(toList());
        }
    }
}
