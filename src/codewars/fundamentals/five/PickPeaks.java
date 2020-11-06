package codewars.fundamentals.five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class PickPeaks {
    private static class Point {
        Point(int position, int value) {
            this.position = position;
            this.value = value;
        }

        public int position;
        public int value;
    }

    private static class Analyzer {

        private static class PlateauState {
            private List<Point> points = new ArrayList<>();

            public void resetIfNotEmpty() {
                if (!points.isEmpty()) {
                    points = new ArrayList<>();
                }
            }

            public void addPoint(Point p) {
                points.add(p);
            }

            public Point getFirstPoint() {
                return points.get(0);
            }

            public boolean isNotEmpty() {
                return !points.isEmpty();
            }
        }

        private enum State {RISE, DOWN}

        private Point lastPoint;
        private List<Point> maximums = new ArrayList<>();
        private List<Point> minimums = new ArrayList<>();
        private State state;
        private PlateauState plateauState = new PlateauState();

        public List<Point> getMaximums() {
            return this.maximums;
        }

        public List<Point> getMinimums() {
            return this.minimums;
        }

        Analyzer(int currentNumber) {
            this.lastPoint = new Point(0, currentNumber);
            this.state = State.DOWN; // set DOWN as initial state to ignore maximum at the edge
        }

        private void updateLastPoint(int value) {
            this.lastPoint = new Point(lastPoint.position + 1, value);

        }

        public void readNextNumber(int number) {
            switch (this.state) {
                case DOWN:
                    if (number > this.lastPoint.value) {
                        // given number is local minimum

                        // check if last points was a plateau
                        // take first point of plateau if yes
                        if (plateauState.isNotEmpty()) {
                            this.minimums.add(plateauState.getFirstPoint());
                        } else {
                            this.minimums.add(this.lastPoint);
                        }

                        this.state = State.RISE;
                        updateLastPoint(number);
                        plateauState.resetIfNotEmpty();
                    } else if (number == this.lastPoint.value) {
                        plateauState.addPoint(this.lastPoint);
                        updateLastPoint(number);
                        // given number is plateau
                        // skip currentNumber update to save first number from plateau as minimum or maximum
                    } else {
                        plateauState.resetIfNotEmpty();
                        updateLastPoint(number);
                    }
                    break;
                case RISE:
                    if (number > this.lastPoint.value) {
                        updateLastPoint(number);
                        plateauState.resetIfNotEmpty();
                    } else if (number == this.lastPoint.value) {
                        plateauState.addPoint(this.lastPoint);
                        updateLastPoint(number);
                        // given number is plateau
                        // keep position to save first number from plateau as minimum or maximum
                    } else {
                        // given number is local maximum;

                        // check if last points was a plateau
                        // take first point of plateau if yes
                        if (plateauState.isNotEmpty()) {
                            this.maximums.add(plateauState.getFirstPoint());
                        } else {
                            this.maximums.add(this.lastPoint);
                        }
                        updateLastPoint(number);
                        this.state = State.DOWN;
                        plateauState.resetIfNotEmpty();
                    }
                    break;
            }
        }
    }

    private static class PeaksMap {
        private Map<String, List<Integer>> peaksMap;

        PeaksMap() {
            this.peaksMap = new HashMap<>();
            this.peaksMap.put("pos", new ArrayList<>());
            this.peaksMap.put("peaks", new ArrayList<>());
        }

        public void add(Point p) {
            this.peaksMap.get("pos").add(p.position);
            this.peaksMap.get("peaks").add(p.value);
        }

        public Map<String, List<Integer>> get() {
            return this.peaksMap;
        }
    }


    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        PeaksMap map = new PeaksMap();

        if (arr.length == 0){
            return map.get();
        }

        Analyzer analyzer = new Analyzer(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            analyzer.readNextNumber(arr[i]);
        }

        for (Point p : analyzer.getMaximums()) {
            map.add(p);
        }
        return map.get();
    }
}