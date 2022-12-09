package codes.dimitri.adventofcode.day9;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public enum Movement {
    LEFT("L"),
    UP("U"),
    DOWN("D"),
    RIGHT("R");

    private final String identifier;

    public static Movement fromIdentifier(String identifier) {
        return Arrays
            .stream(values())
            .filter(direction -> identifier.equals(direction.identifier))
            .findAny()
            .orElseThrow();
    }

    public static List<Movement> fromLine(String line) {
        String[] arguments = line.split(" ");
        Movement movement = Movement.fromIdentifier(arguments[0]);
        int times = Integer.parseInt(arguments[1]);
        return Collections.nCopies(times, movement);
    }
}
