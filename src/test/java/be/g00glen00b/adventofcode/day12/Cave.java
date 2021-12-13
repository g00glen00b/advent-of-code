package be.g00glen00b.adventofcode.day12;

import lombok.Value;

import java.util.Locale;

@Value
public class Cave {
    private static final String START_NAME = "start";
    private static final String END_NAME = "end";
    String name;

    public boolean isSmallCave() {
        return name.toLowerCase(Locale.ROOT).equals(name);
    }

    public boolean isEnd() {
        return END_NAME.equals(name);
    }

    public boolean isStart() {
        return START_NAME.equals(name);
    }

    public static Cave start() {
        return new Cave(START_NAME);
    }
}
