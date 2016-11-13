package com.qrokodial.sparkle.utilities.casting.database;

import com.qrokodial.sparkle.utilities.casting.SafeCast;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CastableStringMap implements CastableDatabase<String, Object> {
    private Map<String, String> map;

    /**
     * Instantiates the class.
     */
    public CastableStringMap() {
        map = new ConcurrentHashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> keySet() {
        return Collections.unmodifiableSet(map.keySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasKey(String key) {
        return map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<String> getString(String key) {
        return Optional.ofNullable(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Byte> getByte(String key) {
        return SafeCast.toByte(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Short> getShort(String key) {
        return SafeCast.toShort(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getInteger(String key) {
        return SafeCast.toInteger(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Long> getLong(String key) {
        return SafeCast.toLong(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Float> getFloat(String key) {
        return SafeCast.toFloat(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getDouble(String key) {
        return SafeCast.toDouble(map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boolean> getBoolean(String key) {
        return SafeCast.toBoolean(map.get(key));
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> valueType) {
        return SafeCast.toType(map.get(key), valueType);
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        if (value == null) {
            remove(key);
        } else {
            map.put(key, value.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Object> remove(String key) {
        return Optional.ofNullable(map.remove(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        map.clear();
    }
}
