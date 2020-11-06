package codewars.fundamentals.five;

import java.util.*;
import java.util.stream.Collectors;

//  https://www.codewars.com/kata/5279f6fe5ab7f447890006a7

public class PickPeaks {
    private static class Point {
        private final int position;
        private final int value;

        Point(int position, int value) {
            this.position = position;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getPosition() {
            return position;
        }
    }

    public static Map<String, List<Integer>> getPeaks(int[] arr) {

        // declare func is asc or desc at current point flag
        boolean ascending = false;

        // declare start of plateau
        Optional<Point> plateauStart = Optional.empty();

        // for each item in input arr:
        // check if maximum:
        //      if (next item is smaller than current)  and (func is asc):
        //          if (start of plateau) :
        //              1) set func state flag to desc
        //              2) save start of plateau as maximum
        //              3) reset start of plateau
        //          else:
        //              1) save current item as maximum
        //      elif (next item is bigger than current):
        //          1) set func state flag to asc
        //          2) reset start of plateau
        //      elif (next item == current):
        //          if (start of plateau): skip
        //

        List<Point> maximums = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i + 1] < arr[i]) && ascending) {
                if (plateauStart.isPresent()) {
                    maximums.add(plateauStart.get());
                    plateauStart = Optional.empty();
                } else {
                    maximums.add(new Point(i, arr[i]));
                }
                ascending = false;
            } else if (arr[i + 1] > arr[i]) {
                ascending = true;
                plateauStart = Optional.empty();
            } else {
                if (plateauStart.isEmpty()) {
                    plateauStart = Optional.of(new Point(i, arr[i]));
                }
            }
        }

        return new HashMap<>() {{
            put("peaks", maximums.stream().map(p -> p.value).collect(Collectors.toList()));
            put("pos", maximums.stream().map(p -> p.position).collect(Collectors.toList()));
        }};
    }
}