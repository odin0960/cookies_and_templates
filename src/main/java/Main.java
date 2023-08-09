import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {

        TimeZone timeZone;
        String zoneId = "UTC";
        int offset = 2;

        String timezone = zoneId + "-" + offset;

//        ZoneId of =


//        String[] split = zoneId.split("-");

//        System.out.println(Set.of(TimeZone.getAvailableIDs()).contains(split[0]));
//        try {
//            if (Integer.parseInt(split[1]) <= 18
//                    || Set.of(TimeZone.getAvailableIDs()).contains(split[0])) {
//                System.out.println("VALID");
//            } else {
//
//            }
//        } catch (ArrayIndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        }

        System.out.println(ZoneId.of(timezone));
        System.out.println(TimeZone.getTimeZone(timezone).getID());

        System.out.println(TimeZone.getTimeZone(timezone));
        System.out.println(TimeZone.getTimeZone(ZoneId.of(timezone)));

        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

        System.out.println(ZonedDateTime.now(ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z")));


    }
}
