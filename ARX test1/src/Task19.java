import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Task19 {
    static ZonedDateTime zonedDateTime = ZonedDateTime.now();

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("e d.M.YY HH:mm:ss.n\nVV");
        String text = dateTimeFormatter.format(zonedDateTime);
        System.out.println(text);
    }
}
