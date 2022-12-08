package codes.dimitri.adventofcode.day8;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static codes.dimitri.adventofcode.TestUtils.longProduct;
import static java.util.function.Predicate.not;

public record TreeGrid(TreePatch[][] patches) {
    public static TreeGrid fromLines(List<String> lines) {
        TreePatch[][] patches = lines
            .stream()
            .map(TreeGrid::fromLine)
            .toArray(TreePatch[][]::new);
        return new TreeGrid(patches);
    }

    private static TreePatch[] fromLine(String line) {
        return line
            .chars()
            .map(charNumber -> charNumber - '0')
            .mapToObj(TreePatch::new)
            .toArray(TreePatch[]::new);
    }

    public Optional<TreePatch> findPatch(Coordinates coordinates) {
        if (coordinates.y() < 0 || coordinates.y() >= patches.length) return Optional.empty();
        if (coordinates.x() < 0 || coordinates.x() >= patches[coordinates.y()].length) return Optional.empty();
        return Optional.of(patches[coordinates.y()][coordinates.x()]);
    }

    public Stream<Coordinates> streamCoordinates() {
        return IntStream
            .range(0, patches.length)
            .boxed()
            .flatMap(y -> IntStream
                .range(0, patches[y].length)
                .mapToObj(x -> new Coordinates(x, y)));
    }

    public long countVisibleTrees() {
        return streamCoordinates()
            .filter(this::isVisibleFromAnySide)
            .count();
    }

    public long calculateHighestScenicScore() {
        return streamCoordinates()
            .mapToLong(this::calculateScenicScore)
            .max()
            .orElse(0);
    }

    public long calculateScenicScore(Coordinates coordinates) {
        return Vector.ONE_STEP_VECTORS
            .stream()
            .mapToLong(vector -> calculateVisibleTrees(coordinates, vector))
            .reduce(1, longProduct());
    }

    public long calculateVisibleTrees(Coordinates coordinates, Vector vector) {
        TreePatch patch = findPatch(coordinates).orElseThrow(() -> new RuntimeException("Patch does not exist"));
        List<TreePatch> allNeighbours = findNeighbours(coordinates, vector);
        if (allNeighbours.isEmpty()) return 0;
        long smallerNeighbours = allNeighbours
            .stream()
            .takeWhile(otherPatch -> otherPatch.isSmallerThan(patch))
            .count();
        if (allNeighbours.size() == smallerNeighbours) return smallerNeighbours;
        else return smallerNeighbours + 1;
    }

    public boolean isVisibleFromAnySide(Coordinates coordinates) {
        return Vector.ONE_STEP_VECTORS
            .stream()
            .anyMatch(vector -> isVisible(coordinates, vector));
    }

    public boolean isVisible(Coordinates coordinates, Vector vector) {
        TreePatch patch = findPatch(coordinates).orElseThrow(() -> new RuntimeException("Patch does not exist"));
        List<TreePatch> neighbours = findNeighbours(coordinates, vector);
        if (neighbours.isEmpty()) return true;
        return neighbours
            .stream()
            .allMatch(neighbour -> neighbour.isSmallerThan(patch));
    }

    public List<TreePatch> findNeighbours(Coordinates coordinates, Vector vector) {
        return Stream
            .iterate(coordinates, c -> c.applyVector(vector))
            .filter(not(coordinates::equals))
            .map(this::findPatch)
            .takeWhile(Optional::isPresent)
            .flatMap(Optional::stream)
            .toList();
    }
}
