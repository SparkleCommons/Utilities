package com.qrokodial.sparkle.utilities.casting.database;

import java.util.Optional;

public interface ReadableCastableDatabase<K> extends ReadableCastablePrimitiveDatabase<K> {
    /**
     * @param key
     * @param valueType
     * @param <T>
     * @return the element casted to the provided type
     */
    <T> Optional<T> get(K key, Class<T> valueType);
}
