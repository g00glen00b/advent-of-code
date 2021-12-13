package be.g00glen00b.adventofcode.day13;

import lombok.Value;
import lombok.With;

import static java.lang.Integer.parseInt;

@With
@Value
public class Coordinate {
    int x;
    int y;

    public static Coordinate fromLine(String line) {
        String[] parameters = line.split(",");
        return new Coordinate(parseInt(parameters[0]), parseInt(parameters[1]));
    }
}
