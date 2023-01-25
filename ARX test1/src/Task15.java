import java.time.Instant;

public class Task15 {
    public static void main(String[] args) {
        System.out.println(getMaxFromMilliseconds());
        System.out.println(getMaxFromSeconds());
        System.out.println(getMaxFromSecondsAndNanos());
    }

    static Instant getMaxFromMilliseconds() {
        long maxLong = Long.MAX_VALUE;
        return Instant.ofEpochMilli(maxLong);
    }

    static Instant getMaxFromSeconds() {
        long number = Instant.MAX.getEpochSecond();
        return Instant.ofEpochSecond(number);
    }

    static Instant getMaxFromSecondsAndNanos() {
        long maxLong = Long.MAX_VALUE;
        return Instant.ofEpochSecond(Instant.MAX.getEpochSecond(),999_999_999);
    }
}
