package com.github.qrokodial.sparkle.utilities.collections;

public class ArrayUtils {
    /**
     * Checks to see if an array contains any specified elements.
     *
     * @param haystack
     * @param needles
     * @return true if a match was found, false otherwise
     */
    public static boolean contains(Object[] haystack, Object... needles) {
        for (Object element : haystack) {
            for (Object needle : needles) {
                if (element.equals(needle)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets the classes backing the array.
     *
     * @param objects
     * @return the class array
     */
    public static Class[] getTypes(Object... objects) {
        Class[] types = new Class[objects.length];

        for (int i = 0; i < objects.length; i++) {
            types[i] = objects[i].getClass();
        }

        return types;
    }
}
