import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTest {
    public static void main(String[] args) {
//        LocalDate date = LocalDate.now();
//        System.out.println(date);
//        Time time = new Time(17,54,00);
//        System.out.println(time);
        Date date1 = new Date();
        System.out.println(date1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
//        System.out.println(dateFormat.format(date1));
//        java.sql.Date date2 = new java.sql.Date(1674531889000L);
//        System.out.println(date2);
//        Date date3 = new Date(123, 12, 25, 20, 40);
//        System.out.println(date3);
//        LocalDate today = LocalDate.now();
//        System.out.println(today);
//        ZoneId id = ZoneId.of("Europe/Moscow");
//        LocalTime todayTime = LocalTime.now(id);
//        System.out.println(todayTime);
//        LocalTime now = LocalTime.now();
//        System.out.println(now.getHour());

        List<LocalTime> timeList = new ArrayList<>();
        timeList.add(LocalTime.now());
        for(int i = 0, count = 0; i < 10000; i++) {
            LocalTime newTime = LocalTime.now();
            if (timeList.get(count).isBefore(newTime)) {
                timeList.add(newTime);
                System.out.println(++count + ". " + timeList.get(count));
            }
        }
//        for (String s: ZoneId.getAvailableZoneIds())
//            System.out.println(s);
    }
}
