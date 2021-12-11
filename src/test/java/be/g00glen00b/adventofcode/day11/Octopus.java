package be.g00glen00b.adventofcode.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
public class Octopus {
    private static final int MAX_ENERGY_LEVEL = 9;
    private int energyLevel;
    private boolean flashing;
    private List<Octopus> adjacent;
    @Getter
    private int flashCount;

    public Octopus(int energyLevel) {
        this(energyLevel, false, List.of(), 0);
    }

    public void setAdjacent(List<Octopus> octopi) {
        this.adjacent = List.copyOf(octopi);
    }

    public void increaseEnergyLevel() {
        if (!this.flashing) {
            this.energyLevel++;
            this.flashing = this.energyLevel > MAX_ENERGY_LEVEL;
            if (flashing) {
                this.flashCount++;
                this.adjacent.forEach(Octopus::increaseEnergyLevel);
            }
        }
    }

    public void reset() {
        if (flashing) {
            this.energyLevel = 0;
            this.flashing = false;
        }
    }

    public boolean isZeroEnergyLevel() {
        return this.energyLevel == 0;
    }
}
