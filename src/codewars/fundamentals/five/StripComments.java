package codewars.fundamentals.five;

// https://www.codewars.com/kata/51c8e37cee245da6b40000bd/java

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {
        return Arrays.stream(text.split("\n"))
                .map(row -> Arrays.stream(
                        row.split(
                                Arrays.stream(commentSymbols)
                                        .map(Pattern::quote)
                                        .collect(Collectors.joining("|"))
                        ))
                        .findFirst()
                        .orElse("").stripTrailing()
                )
                .collect(Collectors.joining("\n"));
    }
}
