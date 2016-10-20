package com.qrokodial.sparkle.utilities.collections;

import com.qrokodial.sparkle.utilities.casting.SafeCast;

import java.lang.reflect.Array;
import java.util.Optional;

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

    /**
     * Attempts to cast an array to a given type.
     *
     * @param type
     * @param objects
     * @param <T>
     * @return the optional-wrapped version of the casted array, if the cast was successful
     */
    public static <T> Optional<T[]> cast(Class<T> type, Object... objects) {
        T[] casted = (T[])Array.newInstance(type, objects.length);
        for (int i = 0; i < objects.length; i++) {
            Optional<T> cast = SafeCast.toType(objects[i], type);

            if (cast.isPresent()) {
                casted[i] = cast.get();
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(casted);
    }

    /**
     * Reverses an array.
     *
     * @param objects
     * @param <T>
     * @return the reversed array
     */
    public static <T> T[] reverse(T... objects) {
        if (objects.length == 0) {
            return objects;
        }

        T[] reversed = (T[])Array.newInstance(objects[0].getClass(), objects.length);
        for (int i = 0; i < objects.length; i++) {
            reversed[i] = objects[objects.length - 1 - i];
        }
        return reversed;
    }
}
