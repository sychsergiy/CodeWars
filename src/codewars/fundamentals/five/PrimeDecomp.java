package codewars.fundamentals.five;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/54d512e62a5e54c96200019e/java

public class PrimeDecomp {

    public static String convertMapToAnswer(SortedMap<Integer, Integer> map) {
        return map.entrySet().stream().map(
                item -> item.getValue() == 1 ?
                        String.format("(%d)", item.getKey()) :
                        String.format("(%d**%d)", item.getKey(), item.getValue())
        ).collect(Collectors.joining());
    }

    public static String factors(int n) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        int divisor = 2;

        while (n != 1) {
            while (n % divisor == 0) {
                n /= divisor;
                if (map.get(divisor) != null) {
                    map.put(divisor, map.get(divisor) + 1);
                } else {
                    map.put(divisor, 1);
                }
            }
            divisor++;
        }
        return convertMapToAnswer(map);
    }
}