import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Set;
import java.util.TimeZone;

@WebFilter("/time/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse res,
                            FilterChain chain)
            throws IOException, ServletException {

        String timezone = Utils.parseZoneId(req);

        try {
            ZoneId.of(timezone);
            chain.doFilter(req, res);
        } catch (DateTimeException e) {
            res.setStatus(404);

            res.setContentType("application/json");
            res.getWriter().write("{\"Error\": \"Invalid timezone\"}");
            res.getWriter().close();
        }


//        if (Utils.validTimeZone(timezone)) {
//            chain.doFilter(req, res);
//        } else {
//            res.setStatus(404);
//
//            res.setContentType("application/json");
//            res.getWriter().write("{\"Error\": \"Invalid timezone\"}");
//            res.getWriter().close();
//        }
    }

//    private String parseZoneId(HttpServletRequest request) {
//        if (request.getParameterMap().containsKey("timezone")) {
//            return request.getParameter("timezone");
//        }
//        return "UTC";
//    }

//    public boolean validTimeZone(String timezone) {
//        return Set.of(TimeZone.getAvailableIDs()).contains(timezone);
//    }


}

//                    String[] validIDs = TimeZone.getAvailableIDs();
//                    for (String str : validIDs) {
//                    if (str != null && str.equals("yourString")) {
//                    System.out.println("Valid ID");
//                    }
//                    }
//
//
//                    boolean valid = DateTimeZone.getAvailableIDs().contains(id);
//
//
//
//                    try {
//                    ZoneId.of("Asia/Kolkataa");
//                    } catch (Exception e) {
//                    e.printStackTrace();
//                    }
