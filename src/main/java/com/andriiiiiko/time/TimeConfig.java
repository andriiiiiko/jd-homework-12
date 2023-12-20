package com.andriiiiiko.time;

import java.util.Arrays;
import java.util.List;

/**
 * This class contains configuration constants related to time and timezones.
 */
public class TimeConfig {

    public static final String DEFAULT_TIMEZONE = "UTC";
    public static final String PARAM_TIMEZONE = "timezone";
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final int MILLISECONDS_IN_HOUR = 3600000; // 60 * 60 * 1000 :)
    protected static final List<String> VALID_TIMEZONES = Arrays.asList(
            "UTC 0", "UTC 1", "UTC 2", "UTC 3", "UTC 4", "UTC 5", "UTC 6", "UTC 7", "UTC 8", "UTC 9", "UTC 10",
            "UTC 11", "UTC 12", "UTC 13", "UTC-1", "UTC-2", "UTC-3", "UTC-4", "UTC-5", "UTC-6", "UTC-7", "UTC-8",
            "UTC-9", "UTC-10", "UTC-11", "UTC-12"
    );
    public static final String TEMPLATES_URL = "C:\\\\Program Files\\\\Apache Software Foundation\\\\Tomcat 10.1\\\\" +
            "webapps\\\\tomcat\\\\WEB-INF\\\\classes\\\\templates\\\\";

    private TimeConfig() {

    }
}
