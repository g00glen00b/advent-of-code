package codes.dimitri.adventofcode.day1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Elves {
    private final LinkedList<Elf> elves;

    public static Elves empty() {
        return new Elves(new LinkedList<>());
    }

    public void addElf() {
        elves.addLast(Elf.empty());;
    }

    public void addInput(String input) {
        if (input.isBlank()) {
            addElf();
        } else {
            int calories = Integer.parseInt(input);
            if (elves.isEmpty()) addElf();
            Elf lastElf = elves.getLast();
            lastElf.addCalories(calories);
        }
    }

    public int calculateMostCaloriesByElf() {
        LinkedList<Elf> copy = new LinkedList<>(elves);
        copy.sort(Comparator.comparing(Elf::getCalories));
        return copy.removeLast().getCalories();
    }

    public int calculateMostCaloriesByTopThreeElves() {
        LinkedList<Elf> copy = new LinkedList<>(elves);
        copy.sort(Comparator.comparing(Elf::getCalories));
        Elf topElf = copy.removeLast();
        Elf secondElf = copy.removeLast();
        Elf thirdElf = copy.removeLast();
        return topElf.getCalories() + secondElf.getCalories() + thirdElf.getCalories();
    }
}
