package codewars.fundamentals.four;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/54eb33e5bc1a25440d000891

public class Decompose {
    public String decompose(long n) {
        for (long i = n - 1; i >= Math.sqrt(n); i--) {
            long rest = n * n;
            List<Long> result = new LinkedList<>();
            long nearestSqrt = i;

            while (rest > 0) {
                result.add(nearestSqrt);
                rest -= nearestSqrt * nearestSqrt;
                if (rest == 0) {
                    Collections.reverse(result);
                    return result.stream().map(String::valueOf).collect(Collectors.joining(" "));
                }
                long nextNearestSqrt = (long) Math.floor(Math.sqrt(rest));
                if (nextNearestSqrt == nearestSqrt) {
                    break;
                } else {
                    nearestSqrt = nextNearestSqrt;
                }
            }
        }
        return null;
    }
}