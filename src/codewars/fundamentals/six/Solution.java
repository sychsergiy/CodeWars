package codewars.fundamentals.six;

// https://www.codewars.com/kata/514b92a657cdc65150000006/java

public class Solution {

    public int calcSumOfSequenceSinceZero(int an, int d) {
        int n = an / d; // amount of items in sequence should be integer
        return n * (d + an) / 2;
    }


    public int solution(int number) {
        number--;
        if (number < 1) {
            return 0;
        }

        int lastElementOfSequenceD3 = number - (number % 3);
        int snD3 = calcSumOfSequenceSinceZero(lastElementOfSequenceD3, 3);

        int lastElementOfSequenceD5 = number - (number % 5);
        int snD5 = calcSumOfSequenceSinceZero(lastElementOfSequenceD5, 5);

        int lastElementOfSequenceD15 = number - (number % 15);
        int snD15 = calcSumOfSequenceSinceZero(lastElementOfSequenceD15, 15);

        return snD3 + snD5 - snD15;
    }
}