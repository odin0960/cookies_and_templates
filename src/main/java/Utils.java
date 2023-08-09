import jakarta.servlet.http.HttpServletRequest;

import java.net.URLEncoder;

public class Utils {
    private static final String DEFAULT_TIME_ZONE = "UTC";

    public static String parseTimeZone(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            String tz = request.getParameter("timezone");
            if (tz == null || tz.isEmpty()) return DEFAULT_TIME_ZONE;
            return URLEncoder.encode(tz); //щоб не писати %2B замість "+" в URL
        }
        return DEFAULT_TIME_ZONE;
    }
}
