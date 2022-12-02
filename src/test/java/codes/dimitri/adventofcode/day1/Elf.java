package codes.dimitri.adventofcode.day1;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Elf {
    private int calories;

    public void addCalories(int calories) {
        this.calories += calories;
    }

    public static Elf empty() {
        return new Elf(0);
    }
}
