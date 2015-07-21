package com.github.qrokodial.sparkle.utilities.casting;

import java.util.Optional;

public interface CastableDatabase<K, V> extends ReadableCastableDatabase<K> {
    /**
     * Stores an element in the database.
     *
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * Removes an element from the database.
     *
     * @param key
     * @return the element removed, if any
     */
    Optional<V> remove(K key);

    /**
     * Clears all elements from the database.
     */
    void clear();
}
