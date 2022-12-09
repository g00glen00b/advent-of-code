package codes.dimitri.adventofcode.day9;

public record Knot(int x, int y) {
    public static Knot initial() {
        return new Knot(0, 0);
    }

    public Knot move(Movement movement) {
        return switch (movement) {
            case UP -> new Knot(x, y - 1);
            case DOWN -> new Knot(x, y + 1);
            case LEFT -> new Knot(x - 1, y);
            case RIGHT -> new Knot(x + 1, y);
        };
    }

    public Knot moveAdjacentTo(Knot aheadKnot) {
        if (aheadKnot.isAdjacent(this) || aheadKnot.isOverlapping(this)) return this;
        int newX = this.x;
        int newY = this.y;
        if (!aheadKnot.isSameXCoordinate(this)) {
            int difference = aheadKnot.x() - this.x;
            int differenceOne = difference / Math.abs(difference);
            newX += differenceOne;
        }
        if (!aheadKnot.isSameYCoordinate(this)) {
            int difference = aheadKnot.y() - this.y;
            int differenceOne = difference / Math.abs(difference);
            newY += differenceOne;
        }
        return new Knot(newX, newY);
    }

    public boolean isSameXCoordinate(Knot other) {
        return this.x == other.x;
    }

    public boolean isSameYCoordinate(Knot other) {
        return this.y == other.y;
    }

    public boolean isOverlapping(Knot other) {
        return this.equals(other);
    }

    public boolean isAdjacent(Knot other) {
        return Math.abs(other.x - this.x) <= 1 && Math.abs(other.y - this.y) <= 1;
    }
}
