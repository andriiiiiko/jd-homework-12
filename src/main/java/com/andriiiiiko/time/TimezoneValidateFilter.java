package com.andriiiiiko.time;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.andriiiiiko.time.TimeConfig.*;

/**
 * A filter for validating the timezone parameter in requests to the /time endpoint.
 */
@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    /**
     * Performs filtering for requests to the /time endpoint.
     *
     * @param request  The ServletRequest object representing the incoming request.
     * @param response The ServletResponse object representing the response to be sent.
     * @param chain    The FilterChain object for invoking the next filter or servlet in the chain.
     * @throws IOException      If an I/O error occurs during filtering.
     * @throws ServletException If a servlet exception occurs during filtering.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        String timezoneParam = request.getParameter(PARAM_TIMEZONE);

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isValidTimezone(timezoneParam)) {
            chain.doFilter(request, response);
        } else {
            handleInvalidTimezone(httpResponse);
        }
    }

    /**
     * Checks if the provided timezone parameter is valid.
     *
     * @param timezoneParam The timezone parameter to be validated.
     * @return True if the timezone parameter is valid or null; otherwise, false.
     */
    private boolean isValidTimezone(String timezoneParam) {
        return VALID_TIMEZONES.contains(timezoneParam) || timezoneParam == null;
    }

    /**
     * Handles the response for an invalid timezone parameter.
     *
     * @param httpResponse The HttpServletResponse object for sending the response.
     * @throws IOException If an I/O error occurs while generating the response.
     */
    private void handleInvalidTimezone(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setContentType(CONTENT_TYPE_HTML);
        httpResponse.setCharacterEncoding("UTF-8");

        httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        try (PrintWriter out = httpResponse.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Invalid timezone</title></head>");
            out.println("<body>");
            out.println("<h2>Invalid timezone</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
