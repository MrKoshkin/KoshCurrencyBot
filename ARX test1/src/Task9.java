import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Task9 {
    public static void main(String[] args) {
        System.out.println(nowExample());
        System.out.println(ofExample());
        System.out.println(ofYearDayExample());
        System.out.println(ofEpochDayExample());
    }

    static LocalDate nowExample() {
        return LocalDate.now();
    }

    static LocalDate ofExample() {
        return LocalDate.of(2020,9,12);
    }

    static LocalDate ofYearDayExample() {
        return LocalDate.ofYearDay(2020, 256);
    }

    static LocalDate ofEpochDayExample() {
        return LocalDate.ofEpochDay(18517L);
    }
}
