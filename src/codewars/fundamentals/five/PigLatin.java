package codewars.fundamentals.five;

// https://www.codewars.com/kata/520b9d2ad5c005041100000f

public class PigLatin {

    private static String toLatin(String word) {
        StringBuilder result = new StringBuilder();
        if (word.length() > 0) {
            result.append(word.substring(1));
            result.append(word.charAt(0));
        }
        result.append("ay");
        return result.toString();
    }

    public static boolean isPunctuation(char symbol) {
        String punctuations = ".,:;!? ";
        return punctuations.contains(Character.toString(symbol));

    }

    public static String pigIt(String str) {
        StringBuilder result = new StringBuilder();

        int i = 0;
        int wordStartIndex = 0;
        boolean wordStarted = false;
        while (i < str.length()) {
            char currentLetter = str.charAt(i);
            if (isPunctuation(currentLetter)) {
                if (wordStarted) {
                    result.append(toLatin(str.substring(wordStartIndex, i)));
                }
                wordStarted = false;
                result.append(currentLetter);
            } else if (i == str.length() - 1) {
                result.append(toLatin(str.substring(wordStartIndex, i + 1)));
            } else {
                if (!wordStarted) {
                    wordStartIndex = i;
                    wordStarted = true;
                }
            }
            i++;
        }
        return result.toString();
    }
}