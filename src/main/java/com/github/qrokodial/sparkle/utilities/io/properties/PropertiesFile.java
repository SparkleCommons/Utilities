package com.github.qrokodial.sparkle.utilities.io.properties;

import com.github.qrokodial.sparkle.utilities.casting.CastableDatabase;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PropertiesFile extends ReadablePropertiesFile implements CastableDatabase<String, String> {
    /**
     * Instantiates the class.
     *
     * @param file
     */
    public PropertiesFile(File file) throws IOException {
        super(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> remove(String key) {
        return map.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        map.clear();
    }
}
