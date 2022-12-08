package codes.dimitri.adventofcode.day8;

import java.util.List;

public record Vector(int size, VectorDirection direction) {
    public static final Vector LEFT_ONE = new Vector(-1, VectorDirection.HORIZONTAL);
    public static final Vector RIGHT_ONE = new Vector(1, VectorDirection.HORIZONTAL);
    public static final Vector UP_ONE = new Vector(-1, VectorDirection.VERTICAL);
    public static final Vector DOWN_ONE = new Vector(1, VectorDirection.VERTICAL);
    public static List<Vector> ONE_STEP_VECTORS = List.of(
        LEFT_ONE,
        RIGHT_ONE,
        UP_ONE,
        DOWN_ONE
    );
}
