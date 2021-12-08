package be.g00glen00b.adventofcode.day4;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class BingoRow {
    List<Integer> numbers;

    public BingoRow mark(int number) {
        int index = numbers.indexOf(number);
        if (index >= 0) {
            List<Integer> copy = new ArrayList<>(numbers);
            copy.remove(index);
            return new BingoRow(List.copyOf(copy));
        } else {
            return this;
        }
    }

    public boolean isComplete() {
        return numbers.isEmpty();
    }
}
