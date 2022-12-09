package codes.dimitri.adventofcode.day9;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Rope {
    private LinkedList<Knot> knots = new LinkedList<>();
    private final Set<Knot> tailPositions = new HashSet<>();

    public static Rope initial(int size) {
        Rope rope = new Rope();
        rope.knots.addAll(Collections.nCopies(size, Knot.initial()));
        return rope;
    }

    public void move(Movement movement) {
        LinkedList<Knot> newKnots = new LinkedList<>();
        for (Knot knot : knots) {
            if (newKnots.isEmpty()) newKnots.add(knot.move(movement));
            else newKnots.add(knot.moveAdjacentTo(newKnots.getLast()));
        }
        this.knots = newKnots;
        tailPositions.add(newKnots.getLast());
    }

    public Set<Knot> getTailPositions() {
        return Set.copyOf(this.tailPositions);
    }
}
