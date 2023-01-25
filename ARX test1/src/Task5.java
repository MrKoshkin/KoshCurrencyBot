import java.util.Date;

public class Task5 {
    static Date birthDate = new Date(1993,11,01);


    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Date date) {
        int dayOfWeek = date.getDay();
        System.out.println(dayOfWeek);
        return switch (dayOfWeek) {
            case 0 -> DayOfWeek.SUNDAY.getTitle();
            case 1 -> DayOfWeek.MONDAY.getTitle();
            case 2 -> DayOfWeek.TUESDAY.getTitle();
            case 3 -> DayOfWeek.WEDNESDAY.getTitle();
            case 4 -> DayOfWeek.THURSDAY.getTitle();
            case 5 -> DayOfWeek.FRIDAY.getTitle();
            case 6 -> DayOfWeek.SATURDAY06.getTitle();
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
