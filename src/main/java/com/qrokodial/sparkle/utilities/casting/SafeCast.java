package com.qrokodial.sparkle.utilities.casting;

import java.util.Optional;

public class SafeCast {
    /**
     * Attempts to cast an object to a given type.
     *
     * @param object
     * @param typeClass
     * @param <T>
     * @return the casted object, if successful
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> toType(Object object, Class<T> typeClass) {
        if (object == null) {
            return Optional.empty();
        }

        if (object instanceof String) {
            if (typeClass.isInstance(Number.class)) {
                if (typeClass.isInstance(Byte.class)) {
                    return (Optional<T>)toByte(object);
                } else if (typeClass.isInstance(Short.class)) {
                    return (Optional<T>)toShort(object);
                } else if (typeClass.isInstance(Integer.class)) {
                    return (Optional<T>)toInteger(object);
                } else if (typeClass.isInstance(Long.class)) {
                    return (Optional<T>)toLong(object);
                } else if (typeClass.isInstance(Float.class)) {
                    return (Optional<T>)toFloat(object);
                } else if (typeClass.isInstance(Double.class)) {
                    return (Optional<T>)toDouble(object);
                }
            } else if (typeClass.isInstance(Boolean.class)) {
                return (Optional<T>)toBoolean(object);
            }
        }

        if (!typeClass.isInstance(object)) {
            return Optional.empty();
        }

        try {
            return Optional.of(typeClass.cast(object));
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

    /**
     * Attempts to cast an object to a string.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<String> toString(Object object) {
        if (object == null) {
            return Optional.empty();
        }

        if (object instanceof String) {
            return Optional.of((String)object);
        }

        return Optional.of(object.toString());
    }

    /**
     * Attempts to cast an object to a byte.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Byte> toByte(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Byte.parseByte((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Byte.class);
    }

    /**
     * Attempts to cast an object to a short.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Short> toShort(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Short.parseShort((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Short.class);
    }

    /**
     * Attempts to cast an object to an integer.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Integer> toInteger(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Integer.parseInt((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Integer.class);
    }

    /**
     * Attempts to cast an object to a long.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Long> toLong(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Long.parseLong((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Long.class);
    }

    /**
     * Attempts to cast an object to a float.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Float> toFloat(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Float.parseFloat((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Float.class);
    }

    /**
     * Attempts to cast an object to a double.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Double> toDouble(Object object) {
        if (object instanceof String) {
            try {
                return Optional.of(Double.parseDouble((String)object));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return toType(object, Double.class);
    }

    /**
     * Attempts to cast an object to a boolean.
     *
     * @param object
     * @return the casted object, if successful
     */
    public static Optional<Boolean> toBoolean(Object object) {
        if (object instanceof String) {
            return Optional.of(Boolean.parseBoolean((String)object));
        }

        return toType(object, Boolean.class);
    }
}
