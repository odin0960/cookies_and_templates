import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet ("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log("reg type: "+req.getMethod());
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<h1>Time</h1>");
//        resp.getWriter().write("reg type: "+req.getMethod());

        String zoneId = Utils.parseZoneId(req);

        String currentTime = ZonedDateTime.now(ZoneId.of(zoneId))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z"));

        resp.getWriter().write(currentTime);

        resp.getWriter().close();
    }

//    private String parseZoneId(HttpServletRequest request) {
//        if (request.getParameterMap().containsKey("timezone")) {
//            return request.getParameter("timezone");
//        }
//        return "UTC";
//    }
}

