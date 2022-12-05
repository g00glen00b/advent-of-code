package codes.dimitri.adventofcode.day5;

import java.util.List;

public record CrateCommand(int amount, CrateMovement movement) {

    public static CrateCommand ofline(String line) {
        String[] words = line.split(" ");
        int amount = Integer.parseInt(words[1]);
        int from = Integer.parseInt(words[3]);
        int to = Integer.parseInt(words[5]);
        return new CrateCommand(amount, new CrateMovement(from, to));
    }

    public static List<CrateCommand> ofLines(List<String> lines) {
        return lines
            .stream()
            .map(CrateCommand::ofline)
            .toList();
    }

    public CrateCommand subtractOne() {
        return new CrateCommand(amount - 1, movement);
    }

    public boolean isEmptyAmount() {
        return amount == 0;
    }
}
