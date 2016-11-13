package com.qrokodial.sparkle.utilities.casting;

import java.util.Optional;
import java.util.Set;

public interface ReadableCastablePrimitiveDatabase<K> {
    /**
     * @return a set of keys in the database
     */
    Set<K> keySet();

    /**
     * @param key
     * @return true if the database contains a key, false otherwise
     */
    boolean hasKey(K key);

    /**
     * @param key
     * @return the string representation of an element in the database
     */
    Optional<String> getString(K key);

    /**
     * @param key
     * @return the byte representation of an element in the database
     */
    Optional<Byte> getByte(K key);

    /**
     * @param key
     * @return the short representation of an element in the database
     */
    Optional<Short> getShort(K key);

    /**
     * @param key
     * @return the integer representation of an element in the database
     */
    Optional<Integer> getInteger(K key);

    /**
     * @param key
     * @return the long representation of an element in the database
     */
    Optional<Long> getLong(K key);

    /**
     * @param key
     * @return the float representation of an element in the database
     */
    Optional<Float> getFloat(K key);

    /**
     * @param key
     * @return the double representation of an element in the database
     */
    Optional<Double> getDouble(K key);

    /**
     * @param key
     * @return the boolean representation of an element in the database
     */
    Optional<Boolean> getBoolean(K key);
}
