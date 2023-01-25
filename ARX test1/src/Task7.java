import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task7 {
    static Calendar birthDate = new GregorianCalendar(1993, Calendar.DECEMBER, 1);

    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Calendar calendar) {
        return switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1 -> DayOfWeek.SUNDAY.getTitle();
            case 2 -> DayOfWeek.MONDAY.getTitle();
            case 3 -> DayOfWeek.TUESDAY.getTitle();
            case 4 -> DayOfWeek.WEDNESDAY.getTitle();
            case 5 -> DayOfWeek.THURSDAY.getTitle();
            case 6 -> DayOfWeek.FRIDAY.getTitle();
            case 7 -> DayOfWeek.SATURDAY06.getTitle();
            default -> "Unknown day of week";
        };
    }
    enum DayOfWeek {
        SUNDAY ("Воскресенье"),
        MONDAY ("Понедельник"),
        TUESDAY ("Вторник"),
        WEDNESDAY ("Среда"),
        THURSDAY ("Четверг"),
        FRIDAY ("Пятница"),
        SATURDAY06 ("Суббота");

        private String title;

        DayOfWeek(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }
    }
}
