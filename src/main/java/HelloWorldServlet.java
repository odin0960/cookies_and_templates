import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringJoiner;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<h1>Hi, Servlet</h1>");
        resp.getWriter().println("Encoding: " + resp.getCharacterEncoding());

        resp.getWriter().write("<br>Параметры</br>");

//        resp.getWriter().write(getAllParameters(req));

        resp.getWriter().write("<br>TIME1</br>");

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                        "dd.MM.yyyy HH:mm:ss"));
//                "Date: dd.MM.yyyy, Time: HH:mm:ss O"));
        resp.getWriter().write(currentTime);

        resp.getWriter().write("<br>TIME2</br>");

//        resp.getWriter().write(Instant.now().toString());
//        resp.getWriter().write(Instant.now().atOffset(ZoneOffset.UTC).toString());
//        resp.getWriter().write(String.valueOf(OffsetDateTime.now(ZoneOffset.UTC)));
//
//        resp.getWriter().write("<br>TIME3</br>");
//
        String newTime2 = ZonedDateTime.now(ZoneId.of("UTC+2"))
                .format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss z"));
        resp.getWriter().write(newTime2);

        resp.getWriter().write("<br>Parameters</br>");
        resp.getWriter().write(getAllParametersUrlEncoded(req));

        resp.getWriter().write("<br>Headers</br>");
        resp.getWriter().write(getAllHeaders(req));

//        resp.getWriter().write(String.valueOf(Date.from(ZonedDateTime.now().toInstant())));

        resp.getWriter().close();
    }

    private String getAllParametersUrlEncoded(HttpServletRequest request) {
        StringJoiner result = new StringJoiner("<br>");

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValues = Arrays.toString(request.getParameterValues(parameterName));

            result.add(parameterName + " = " + parameterValues);
        }
        return result.toString();
    }

    private String getAllHeaders(HttpServletRequest request) {
        StringJoiner result = new StringJoiner("<br>");

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            result.add(headerName + " = " + headerValue);
        }
        return result.toString();
    }


    private String parseZoneId(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("zoneId")) {
            return request.getParameter("zoneId");
        }

        return "unnamed";
    }

}