package codewars.fundamentals.four;

import java.util.*;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/5629db57620258aa9d000014

class LettersCounter {
    private final static int firstChar = 97; // a - 97
    private final static int lastChar = 122; // z - 122
    public final static int alphabetLength = lastChar - firstChar + 1; // works only for small letters

    private final int[] countingArray = new int[alphabetLength];

    private int calcCountingArrayIndex(int c) {
        return c - firstChar;
    }

    public void add(int c) {
        countingArray[calcCountingArrayIndex(c)]++;
    }

    public static char getCharByIndex(int i) {
        return (char) (i + firstChar);
    }

    public int getCountByIndex(int i) {
        return countingArray[i];
    }
}

public class Mixing {
    enum WORD {
        first(2), second(1), both(0);

        private final int value;

        WORD(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static class ComparisonResult {
        private final WORD word;
        private final char letter;
        private final int maxAmount;

        ComparisonResult(WORD word, char letter, int maxAmount) {
            this.word = word;
            this.letter = letter;
            this.maxAmount = maxAmount;
        }

        @Override
        public String toString() {
            String delimiter = "";
            if (this.word == WORD.first) {
                delimiter = "1:";
            } else if (this.word == WORD.second) {
                delimiter = "2:";
            } else if (this.word == WORD.both) {
                delimiter = "=:";
            }
            return delimiter + Character.toString(letter).repeat(this.maxAmount);
        }
    }

    public static boolean isSmallLetter(int c) {
        return c >= 96 && c <= 122;
    }

    public static LettersCounter createLettersCounter(String s) {
        LettersCounter counter = new LettersCounter();
        s.chars().filter(Mixing::isSmallLetter).forEach(counter::add);
        return counter;
    }

    public static Optional<ComparisonResult> compareLetter(int lettersCount1, int lettersCount2, char letter) {
        if (lettersCount1 > 1 || lettersCount2 > 1) {
            int diffCount = lettersCount1 - lettersCount2;
            if (diffCount >= 1) {
                return Optional.of(new ComparisonResult(WORD.first, letter, lettersCount1));
            } else if (diffCount <= -1) {
                return Optional.of(new ComparisonResult(WORD.second, letter, lettersCount2));
            } else {
                return Optional.of(new ComparisonResult(WORD.both, letter, lettersCount1));
            }
        }
        return Optional.empty();
    }

    public static List<ComparisonResult> compareLetters(LettersCounter a1, LettersCounter a2) {
        List<ComparisonResult> comparisons = new ArrayList<>();
        for (int i = 0; i < LettersCounter.alphabetLength; i++) {
            int a1LetterCount = a1.getCountByIndex(i);
            int a2LetterCount = a2.getCountByIndex(i);
            char letter = LettersCounter.getCharByIndex(i);
            compareLetter(a1LetterCount, a2LetterCount, letter).ifPresent(comparisons::add);
        }
        return comparisons;
    }

    public static String mix(String s1, String s2) {
        LettersCounter a1 = createLettersCounter(s1);
        LettersCounter a2 = createLettersCounter(s2);
        List<ComparisonResult> comparisons = compareLetters(a1, a2);

        return comparisons.stream().sorted((o1, o2) -> {
            int r = Integer.compare(o2.maxAmount, o1.maxAmount);
            if (r == 0) {
                return Integer.compare(o2.word.getValue(), o1.word.getValue());
            } else {
                return r;
            }
        }).map(ComparisonResult::toString).collect(Collectors.joining("/"));
    }
}