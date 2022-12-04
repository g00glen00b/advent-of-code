package codes.dimitri.adventofcode.day3;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;

public record Item(char item, int priority) {
    public static Optional<Item> ofCharacter(char item) {
        if (item >= 'a' && item <= 'z') {
            int priority = item - 'a' + 1;
            return Optional.of(new Item(item, priority));
        } else if (item >= 'A' && item <= 'Z') {
            int priority = item - 'A' + 27;
            return Optional.of(new Item(item, priority));
        } else {
            return Optional.empty();
        }
    }

    public static Set<Item> ofString(String items) {
        return items
            .chars()
            .mapToObj(item -> Item.ofCharacter((char) item))
            .flatMap(Optional::stream)
            .collect(toUnmodifiableSet());
    }
}
