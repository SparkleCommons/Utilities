package com.qrokodial.sparkle.utilities.io.properties;

import com.qrokodial.sparkle.utilities.casting.database.CastableStringMap;
import com.qrokodial.sparkle.utilities.casting.database.ReadableCastableDatabase;
import com.qrokodial.sparkle.utilities.io.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class ReadablePropertiesFile implements ReadableCastableDatabase<String> {
    protected CastableStringMap map;

    /**
     * Instantiates the class.
     *
     * @param inputStream
     */
    public ReadablePropertiesFile(InputStream inputStream) throws IOException {
        map = new CastableStringMap();
        reload(inputStream);
    }

    /**
     * Reloads the contents of the database.
     */
    protected void reload(InputStream inputStream) throws IOException {
        map.clear();

        String contents = StreamUtils.readFully(inputStream, "UTF-8");
        String[] split = contents.replace("\r", "").split("\n");

        for (String line : split) {
            if (line.contains("#")) {
                line = line.substring(0, line.indexOf('#'));
            }

            if (line.contains("=")) {
                String key = line.substring(0, line.indexOf('='));
                map.put(key, line.substring(key.length() + "=".length()));
            }
        }
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
        return map.hasKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getString(String key) {
        return map.getString(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Byte> getByte(String key) {
        return map.getByte(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Short> getShort(String key) {
        return map.getShort(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getInteger(String key) {
        return map.getInteger(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Long> getLong(String key) {
        return map.getLong(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Float> getFloat(String key) {
        return map.getFloat(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getDouble(String key) {
        return map.getDouble(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Boolean> getBoolean(String key) {
        return map.getBoolean(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Optional<T> get(String key, Class<T> valueType) {
        return map.get(key, valueType);
    }
}
