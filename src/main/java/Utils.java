import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;


public class Utils {
    private static final String DEFAULT_TIME_ZONE = "UTC";

    public static String getTimezoneFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTimezone")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String parseTimeZone(HttpServletRequest req) {

        if (req.getParameterMap().containsKey("timezone")) {
            String tz = req.getParameter("timezone");
            if (tz == null || tz.isEmpty()) {
                if (getTimezoneFromCookie(req) != null) {
                    return getTimezoneFromCookie(req);
                } else {
                    return DEFAULT_TIME_ZONE;
                }
            }
            return tz;
        } else if (getTimezoneFromCookie(req) != null) {
            return getTimezoneFromCookie(req);
        }
        return DEFAULT_TIME_ZONE;
    }
}
