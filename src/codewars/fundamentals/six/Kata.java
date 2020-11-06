package codewars.fundamentals.six;


import java.util.Arrays;
import java.util.Optional;

// https://www.codewars.com/kata/5839edaa6754d6fec10000a2

public class Kata {
    private static class Array {
        public static char[] getFirstHalf(char[] array) {
            return Arrays.copyOfRange(array, 0, (array.length + 1) / 2);
        }

        public static char[] getSecondHalf(char[] array) {
            return Arrays.copyOfRange(array, (array.length + 1) / 2, array.length);
        }

        public static char[] getMiddleEdge(char[] array) {
            if (array.length % 2 == 0) {
                return new char[]{array[array.length / 2 - 1], array[array.length / 2]};
            } else {
                return new char[]{array[array.length / 2], array[(array.length + 1) / 2]};
            }
        }

        public static char getFirstItem(char[] array) {
            return array[0];
        }

        public static char getLastItem(char[] array) {
            return array[array.length - 1];
        }
    }

    public static boolean isLetterSkipped(char[] array) {
        return ((int) Array.getLastItem(array) - (int) Array.getFirstItem(array)) > 1;
    }

    private static char calcChar(char ch) {
        return (char) ((int) ch + 1);
    }

    public static Optional<Character> calcSkippedLetterIfPresent(char[] array) {
        if (isLetterSkipped(array)) {
            if (array.length > 3) {
                return calcMissingLetter(array);
            } else if (array.length == 3) {
                if (array[1] - array[0] == 2) {
                    return Optional.of(calcChar(array[0]));
                } else if (array[2] - array[1] == 2) {
                    return Optional.of(calcChar(array[1]));
                } else {
                    return Optional.empty();
                }
            } else {
                return Optional.of(calcChar(array[0]));
            }
        }
        return Optional.empty();
    }

    private static Optional<Character> calcMissingLetter(char[] array) {
        Optional<Character> skippedLetter;

        char[] firstHalf = Array.getFirstHalf(array);
        skippedLetter = calcSkippedLetterIfPresent(firstHalf);
        if (skippedLetter.isPresent()) {
            return skippedLetter;
        } else {
            char[] secondHalf = Array.getSecondHalf(array);
            skippedLetter = calcSkippedLetterIfPresent(secondHalf);
            if (skippedLetter.isPresent()) {
                return skippedLetter;
            } else {
                char[] edge = Array.getMiddleEdge(array);
                skippedLetter = calcSkippedLetterIfPresent(edge);
                if (skippedLetter.isPresent()) {
                    return skippedLetter;
                }
            }
        }
        return Optional.empty();
    }

    public static char findMissingLetter(char[] array) {
        return calcMissingLetter(array).get();
    }
}