package codes.dimitri.adventofcode.day3;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public record Rucksack(Set<Item> leftCompartment, Set<Item> rightCompartment) {
    public static Rucksack ofString(String items) {
        int itemsPerCompartment = items.length() / 2;
        String itemStringLeftCompartment = items.substring(0, itemsPerCompartment);
        String itemStringRightCompartment = items.substring(itemsPerCompartment);
        Set<Item> itemsLeftCompartment = Item.ofString(itemStringLeftCompartment);
        Set<Item> itemsRightCompartment = Item.ofString(itemStringRightCompartment);
        return new Rucksack(itemsLeftCompartment, itemsRightCompartment);
    }

    public List<Item> findOverlappingItems() {
        return leftCompartment
            .stream()
            .filter(rightCompartment::contains)
            .toList();
    }

    public Set<Item> findAllItems() {
        Set<Item> items = new HashSet<>(leftCompartment);
        items.addAll(rightCompartment);
        return Set.copyOf(items);
    }
}
