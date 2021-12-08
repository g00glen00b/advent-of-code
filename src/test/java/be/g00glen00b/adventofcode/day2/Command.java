package be.g00glen00b.adventofcode.day2;

import lombok.Value;

import java.util.Locale;

@Value
public class Command {
    CommandType type;
    int distance;

    public static Command fromLine(String line) {
        String[] arguments = line.split(" ");
        CommandType type = CommandType.valueOf(arguments[0].toUpperCase(Locale.ROOT));
        int distance = Integer.parseInt(arguments[1]);
        return new Command(type, distance);
    }
}
