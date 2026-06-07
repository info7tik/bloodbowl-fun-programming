package fr.bloodbowl.mock;

import java.util.List;
import java.util.stream.IntStream;

import fr.bloodbowl.dices.IntegerGenerator;

public class MockRandomInteger implements IntegerGenerator {
    private int score = -1;

    @Override
    public List<Integer> generate(int numberOfValues, int minValue, int maxValue) {
        return IntStream.range(0, numberOfValues).map(unused -> score).boxed().toList();
    }

    public void setScore(int score) {
        this.score = score;
    }
}
