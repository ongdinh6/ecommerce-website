package vn.omdinh.demo.utils;

public class StringUtils {

    public static String valueAsDefault(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

}
