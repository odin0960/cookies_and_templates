import jakarta.servlet.http.HttpServletRequest;

import java.time.DateTimeException;
import java.util.Set;
import java.util.TimeZone;

public class Utils {

    public static String parseZoneId(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            return request.getParameter("timezone");
        }
        return "UTC";
    }

    public static boolean validTimeZone(String timezone) {
        return Set.of(TimeZone.getAvailableIDs()).contains(timezone);

//        try{
//
//        } catch (DateTimeException e) {
//
//        }
    }
}
