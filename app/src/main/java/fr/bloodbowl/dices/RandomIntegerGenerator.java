package fr.bloodbowl.dices;

import java.security.SecureRandom;
import java.util.List;

public class RandomIntegerGenerator implements IntegerGenerator {
    private final SecureRandom random = new SecureRandom();

    @Override
    public List<Integer> generate(int numberOfValues, int minValue, int maxValue) {
        return random.ints(numberOfValues, minValue, maxValue + 1).boxed().toList();
    }

}
