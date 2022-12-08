package codes.dimitri.adventofcode.day8;

public record Coordinates(int x, int y) {
    public Coordinates applyVector(Vector vector) {
        return switch (vector.direction()) {
            case VERTICAL -> new Coordinates(x, y + vector.size());
            case HORIZONTAL -> new Coordinates(x + vector.size(), y);
        };
    }
}
