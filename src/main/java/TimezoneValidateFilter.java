import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter("/time/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain)
            throws IOException, ServletException {

        String timezone = Utils.parseTimeZone(req);

        try {
            ZoneId.of(timezone);
            chain.doFilter(req, resp);
        } catch (DateTimeException e) {
            resp.setStatus(404);

            resp.setContentType("application/json");
            resp.getWriter().write("{\"Error\": \"Invalid timezone\"}");
            resp.getWriter().close();
        }
    }
}
