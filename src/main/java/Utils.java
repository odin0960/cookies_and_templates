import jakarta.servlet.http.HttpServletRequest;

public class Utils {

    public static String parseZoneId(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            return request.getParameter("timezone");
        }
        return "UTC";
    }
}
