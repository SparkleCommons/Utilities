package com.github.qrokodial.sparkle.utilities.characters;

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
}
