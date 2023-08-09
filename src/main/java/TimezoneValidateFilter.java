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
    }
}
