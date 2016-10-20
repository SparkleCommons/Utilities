package com.qrokodial.sparkle.utilities.casting;

public class NullChecker {
    /**
     * Throws an exception if the object is null.
     *
     * @param object the object to check if null
     * @param exception the exception to throw if the object is null
     * @param <T>
     * @param <E>
     * @return the object, assuming it wasn't null
     * @throws E the exception originally passed to this function
     */
    public static <T, E extends Exception> T notNullOrThrow(T object, E exception) throws E {
        if (object == null) {
            throw exception;
        }

        return object;
    }
}
