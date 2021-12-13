package be.g00glen00b.adventofcode.day12;

import lombok.Value;

import java.util.List;

@Value
public class CaveLink {
    Cave start;
    Cave end;

    public static CaveLink fromLine(String line) {
        String[] arguments = line.split("-");
        return new CaveLink(new Cave(arguments[0]), new Cave(arguments[1]));
    }

    public boolean isContaining(Cave cave) {
        return start.equals(cave) || end.equals(cave);
    }

    public List<Cave> getCaves() {
        return List.of(start, end);
    }
}
