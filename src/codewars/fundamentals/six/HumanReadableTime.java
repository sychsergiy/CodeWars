package codewars.fundamentals.six;

// https://www.codewars.com/kata/52685f7382004e774f0001f7/java

public class HumanReadableTime {
    private static final int secondsInMinute = 60;
    private static final int secondsInHour = 3600;

    public static String makeReadable(int seconds) {
        int hoursPart = seconds / secondsInHour;
        seconds = seconds % secondsInHour;
        int minutesPart = seconds / secondsInMinute;
        int secondsPart = seconds % secondsInMinute;
        return String.format("%02d:%02d:%02d", hoursPart, minutesPart, secondsPart);
    }
}
