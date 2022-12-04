package codes.dimitri.adventofcode.day3;

import java.util.Optional;
import java.util.Set;

public record Badge(Item badgeItem) {
    public static Optional<Badge> ofRucksacks(Rucksack rucksack1, Rucksack rucksack2, Rucksack rucksack3) {
        Set<Item> itemsRucksack1 = rucksack1.findAllItems();
        Set<Item> itemsRucksack2 = rucksack2.findAllItems();
        Set<Item> itemsRucksack3 = rucksack3.findAllItems();
        return itemsRucksack1
            .stream()
            .filter(itemsRucksack2::contains)
            .filter(itemsRucksack3::contains)
            .findAny()
            .map(Badge::new);
    }
}
