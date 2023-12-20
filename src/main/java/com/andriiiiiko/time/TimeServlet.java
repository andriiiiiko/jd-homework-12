package com.andriiiiiko.time;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.andriiiiiko.time.TimeConfig.*;

/**
 * A servlet for displaying the current time in a specified timezone.
 */
@WebServlet(urlPatterns = "/time")
public class TimeServlet extends HttpServlet {

    /**
     * The template engine for rendering HTML templates.
     */
    private transient TemplateEngine engine;

    /**
     * Initializes the servlet by setting up the Thymeleaf template engine with custom settings.
     */
    @Override
    public void init() {
        engine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix(TEMPLATES_URL);
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);
    }

    /**
     * Handles the HTTP GET request to display the current time in the specified timezone.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws IOException if there is an I/O error while processing the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_HTML);

        TimeZone timezone = TimeZone.getTimeZone(DEFAULT_TIMEZONE);

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastTimezone".equals(cookie.getName())) {
                    String lastTimeZone = cookie.getValue();
                    String stringZoneID = lastTimeZone.substring(3).trim();
                    int zoneId = Integer.parseInt(stringZoneID);
                    timezone.setRawOffset(zoneId * MILLISECONDS_IN_HOUR);
                    break;
                }
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timezone);
        String currentTime = dateFormat.format(new Date());

        Context context = new Context();
        context.setVariable("currentTime", currentTime);

        engine.process("time", context, response.getWriter());
        response.getWriter().close();
    }
}
