import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet(value = "/time")
public class ThymeleafTime extends HttpServlet {
    private TemplateEngine engine;

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("./templates/"); //де шукати шаблони
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);

        engine.addTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        String timezone = Utils.parseTimeZone(req);

        resp.addCookie(new Cookie("lastTimezone", timezone));

        String currentTime = ZonedDateTime.now(ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z"));

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("time", currentTime)
        );

        engine.process("time", simpleContext, resp.getWriter());

        resp.getWriter().close();
    }
}

