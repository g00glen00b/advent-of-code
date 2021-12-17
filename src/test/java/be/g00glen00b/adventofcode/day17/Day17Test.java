package be.g00glen00b.adventofcode.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day17Test {
    @Test
    void partOne_hit() {
        TargetArea area = new TargetArea(20, -10, 10, 5);
        Probe probe = new Probe(0, 0, 0, 7, 2);
        assertThat(TrajectoryCalculator.calculateHit(area, probe)).isNotEmpty();
    }

    @Test
    void partOne_hit2() {
        TargetArea area = new TargetArea(20, -10, 10, 5);
        Probe probe = new Probe(0, 0, 0, 6, 3);
        assertThat(TrajectoryCalculator.calculateHit(area, probe)).isNotEmpty();
    }

    @Test
    void partOne_miss() {
        TargetArea area = new TargetArea(20, -10, 10, 5);
        Probe probe = new Probe(0, 0, 0, 17, -4);
        assertThat(TrajectoryCalculator.calculateHit(area, probe)).isEmpty();
    }

    @Test
    void partOne_example() {
        TargetArea area = new TargetArea(20, -10, 10, 5);
        Probe probe = TrajectoryCalculator.calculateHighestHit(area);
        assertThat(probe.getHighestY()).isEqualTo(45);
    }

    @Test
    void partOne_full() {
        TargetArea area = new TargetArea(34, -215, 33, 29);
        Probe probe = TrajectoryCalculator.calculateHighestHit(area);
        assertThat(probe.getHighestY()).isEqualTo(23005);
    }

    @Test
    void partTwo_example() {
        TargetArea area = new TargetArea(20, -10, 10, 5);
        List<Probe> probe = TrajectoryCalculator.calculateAllHits(area);
        assertThat(probe.size()).isEqualTo(112);
    }

    @Test
    void partTwo_full() {
        TargetArea area = new TargetArea(34, -215, 33, 29);
        List<Probe> probe = TrajectoryCalculator.calculateAllHits(area);
        assertThat(probe.size()).isEqualTo(112);
    }
}
