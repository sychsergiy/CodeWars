package codewars.fundamentals.six;

import java.util.Arrays;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/5264d2b162488dc400000001

public class SpinWords {

    public String spinWords(String sentence) {
        return Arrays.stream(sentence.split(" ")).map(word -> {
                    if (word.length() >= 5) {
                        return new StringBuilder(word).reverse().toString();
                    } else {
                        return word;
                    }
                }
        ).collect(Collectors.joining(" "));
    }
}