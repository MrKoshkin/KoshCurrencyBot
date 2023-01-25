import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TreeSet;

public class Task17 {
    public static void main(String[] args) {
        TreeSet<String> sortedZones = getSortedZones();
        System.out.println(sortedZones.size());
        System.out.println(sortedZones.first());
        System.out.println(sortedZones.last());

        System.out.println(getBeijingDateTime());
    }

    static TreeSet<String> getSortedZones() {
        TreeSet<String> result = new TreeSet<>();
        result.addAll(ZoneId.getAvailableZoneIds());

        return result;
    }

    static ZonedDateTime getBeijingDateTime() {

        return ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    }
}
