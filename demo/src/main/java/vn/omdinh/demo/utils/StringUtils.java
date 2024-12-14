package vn.omdinh.demo.utils;

import java.util.UUID;

public class StringUtils {

    public static String valueAsDefault(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }
}
