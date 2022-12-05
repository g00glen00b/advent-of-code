package codes.dimitri.adventofcode.day5;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CrateStacks {
    private final List<LinkedList<Crate>> stacks;

    public static CrateStacks ofLines(List<String> lines) {
        int amountOfStacks = calculateAmountOfStacks(lines);
        int maximumStackDepth = calculateStackDepth(lines);
        List<LinkedList<Crate>> crateStacks = new ArrayList<>(amountOfStacks);
        for (int depth = 0; depth < maximumStackDepth; depth++) {
            String line = lines.get(depth);
            for (int stackIndex = 0; stackIndex < amountOfStacks; stackIndex++) {
                LinkedList<Crate> crateStack = findOrCreateStackForIndex(crateStacks, stackIndex);
                calculateCrateForIndex(line, stackIndex).ifPresent(crateStack::addFirst);
            }
        }
        return new CrateStacks(crateStacks);
    }

    private static LinkedList<Crate> findOrCreateStackForIndex(List<LinkedList<Crate>> crateStacks, int stackIndex) {
        if (crateStacks.size() <= stackIndex) {
            crateStacks.add(new LinkedList<>());
            return findOrCreateStackForIndex(crateStacks, stackIndex);
        } else {
            return crateStacks.get(stackIndex);
        }
    }

    private static Optional<Crate> calculateCrateForIndex(String line, int stackIndex) {
        int stringIndex = stackIndex * 4 + 1;
        if (stringIndex >= line.length()) return Optional.empty();
        char character = line.charAt(stringIndex);
        if (character == ' ') return Optional.empty();
        return Optional.of(new Crate(character));
    }

    private static int calculateStackDepth(List<String> lines) {
        return lines.size() - 1;
    }

    private static int calculateAmountOfStacks(List<String> lines) {
        return lines
            .stream()
            .mapToInt(line -> (line.length() + 1) / 4)
            .max()
            .orElse(0);
    }

    public void execute9000(CrateCommand command) {
        if (command.isEmptyAmount()) return;
        Crate crate = stacks.get(command.movement().from() - 1).removeLast();
        stacks.get(command.movement().to() - 1).addLast(crate);
        execute9000(command.subtractOne());
    }

    public void execute9001(CrateCommand command) {
        LinkedList<Crate> movableCrates = new LinkedList<>();
        LinkedList<Crate> fromStack = stacks.get(command.movement().from() - 1);
        for (int attempt = 0; attempt < command.amount(); attempt++) {
            movableCrates.addFirst(fromStack.removeLast());
        }
        stacks.get(command.movement().to() - 1).addAll(movableCrates);
    }

    public void execute9000(List<CrateCommand> commands) {
        commands.forEach(this::execute9000);
    }

    public void execute9001(List<CrateCommand> commands) {
        commands.forEach(this::execute9001);
    }

    public List<Crate> findTop() {
        return stacks
            .stream()
            .map(LinkedList::getLast)
            .toList();
    }
}
