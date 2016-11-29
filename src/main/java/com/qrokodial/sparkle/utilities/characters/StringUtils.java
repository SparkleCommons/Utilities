package com.qrokodial.sparkle.utilities.characters;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    /**
     * Checks to see if a string starts with another string in a case-insensitive manner.
     *
     * @param haystack the main string
     * @param needle the string to test against the main string
     * @return true if it matches, false otherwise
     */
    public static boolean startsWithIgnoreCase(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return false;
        }

        for (int i = 0; i < needle.length(); i++) {
            if (!haystack.substring(i, i + 1).equalsIgnoreCase(needle.substring(i, i + 1))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks to see if a string ends with another string in a case-insensitive manner.
     *
     * @param haystack the main string
     * @param needle the string to test against the main string
     * @return true if it matches, false otherwise
     */
    public static boolean endsWithIgnoreCase(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return false;
        }

        for (int i = 0; i < needle.length(); i++) {
            int start = haystack.length() - needle.length() + i;
            if (!haystack.substring(start, start + 1).equalsIgnoreCase(needle.substring(i, i + 1))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Combines an array of objects into their string representation.
     *
     * @param fragments the fragments combined
     * @return the combined string representation
     */
    public static String combine(Object... fragments) {
        StringBuilder buffer = new StringBuilder();

        for (Object fragment : fragments) {
            if (fragment instanceof Iterable) {
                byte i = 0;

                for (Object element : (Iterable)fragment) {
                    buffer.append(element);
                    buffer.append(", ");
                    i++;
                }

                if (i > 0) {
                    buffer.setLength(buffer.length() - ", ".length());
                }
            } else if (fragment.getClass().isArray()) {
                Object[] array = (Object[])fragment;

                if (array.length > 0) {
                    for (Object element : array) {
                        buffer.append(element);
                        buffer.append(", ");
                    }

                    buffer.setLength(buffer.length() - ", ".length());
                }
            } else {
                buffer.append(fragment);
            }
        }

        return buffer.toString();
    }

    /**
     * Parses an array of string arguments so that things wrapped in quotes count as one element in the array.
     *
     * @param arguments the arguments to sanitize
     * @param delimiter the delimiter to insert in between the elements in quotes (ie: " ")
     * @return the sanitized array of string arguments
     */
    public static String[] parseArguments(String[] arguments, String delimiter) {
        List<String> sanitized = new ArrayList<>();

        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].startsWith("\"")) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(arguments[i].substring("\"".length()));
                buffer.append(delimiter);

                for (i++; i < arguments.length; i++) {
                    if (arguments[i].endsWith("\"")) {
                        buffer.append(arguments[i].substring(0, arguments[i].length() - "\"".length()));
                        buffer.append(delimiter);
                        break;
                    } else {
                        buffer.append(arguments[i]);
                        buffer.append(delimiter);
                    }
                }

                sanitized.add(buffer.toString());
            } else {
                sanitized.add(arguments[i]);
            }
        }

        return sanitized.toArray(new String[0]);
    }
}
