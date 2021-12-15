package be.g00glen00b.adventofcode.day15;

import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
public class Coordinates {
    int x;
    int y;

    public int calculateXYProduct() {
        return x * y;
    }

    public List<Coordinates> calculateAdjacent() {
        return List.of(
            withX(x - 1),
            withX(x + 1),
            withY(y - 1),
            withY(y + 1)
        );
    }
}
