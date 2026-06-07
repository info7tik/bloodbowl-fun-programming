package fr.bloodbowl.dices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class RandomIntegerGeneratorTest {
    private final IntegerGenerator generator = new RandomIntegerGenerator();
    private final int minValue = 3;
    private final int maxValue = 5;

    @Test
    void generateOneNumberBetween3IncludedAnd5IncludedWithSuccess() {
        int numberOfNumbers = 1;

        List<Integer> results = generator.generate(numberOfNumbers, minValue, maxValue);

        assertEquals(numberOfNumbers, results.size());
        assertTrue(results.stream().allMatch(nb -> nb >= minValue || nb <= maxValue));
    }

    @Test
    void generateThreeNumbersBetween3IncludedAnd5IncludedWithSuccess() {
        int numberOfNumbers = 3;
        List<Integer> results = generator.generate(numberOfNumbers, minValue, maxValue);

        assertEquals(numberOfNumbers, results.size());
        assertTrue(results.stream().allMatch(nb -> nb >= minValue || nb <= maxValue));
    }
}
