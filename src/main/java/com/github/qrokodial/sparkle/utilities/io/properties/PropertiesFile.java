package com.github.qrokodial.sparkle.utilities.io.properties;

import com.github.qrokodial.sparkle.utilities.casting.CastableDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class PropertiesFile extends ReadablePropertiesFile implements CastableDatabase<String, String> {
    private File file;

    /**
     * Instantiates the class.
     *
     * @param file
     */
    public PropertiesFile(File file) throws IOException {
        super(new FileInputStream(file));
    }

    /**
     * @return the underlying file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the underlying file.
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Reloads the contents of the database.
     */
    public void reload() throws IOException {
        super.reload(new FileInputStream(getFile()));
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
