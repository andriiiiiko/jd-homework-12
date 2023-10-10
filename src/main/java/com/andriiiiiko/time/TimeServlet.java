package com.andriiiiiko.time;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
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
     * Handles GET requests to the /time endpoint.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_HTML);

        String timezoneParam = request.getParameter(PARAM_TIMEZONE);

        TimeZone timezone = TimeZone.getTimeZone(DEFAULT_TIMEZONE);

        if (timezoneParam != null) {
            String stringZoneID = timezoneParam.substring(3).trim();

            int zoneId = Integer.parseInt(stringZoneID);
            timezone.setRawOffset(zoneId * MILLISECONDS_IN_HOUR);
        }

        handleValidTimezone(timezone, response);
    }

    /**
     * Handles the response for a valid timezone.
     *
     * @param timezone The TimeZone object representing the desired timezone.
     * @param response The HTTP response object.
     * @throws IOException If an I/O error occurs while generating the response.
     */
    private void handleValidTimezone(TimeZone timezone, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timezone);
        String currentTime = dateFormat.format(new Date());

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Current Time</title></head>");
            out.println("<body>");
            out.println("<h2>Current Time:</h2>");
            out.println("<p>" + currentTime + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
