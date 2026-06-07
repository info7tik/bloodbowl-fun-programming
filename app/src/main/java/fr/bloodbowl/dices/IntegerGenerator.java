package fr.bloodbowl.dices;

import java.util.List;

public interface IntegerGenerator {
    List<Integer> generate(int numberOfValues, int minValueInclusive, int maxValueInclusive);
}
