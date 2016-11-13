package com.qrokodial.sparkle.utilities.io.properties;

import com.qrokodial.sparkle.utilities.casting.database.CastableDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

public class PropertiesFile extends ReadablePropertiesFile implements CastableDatabase<String, Object> {
    private File file;

    /**
     * Instantiates the class.
     *
     * @param file
     */
    public PropertiesFile(File file) throws IOException {
        super(new FileInputStream(file));
        this.file = file;
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
     * Saves the contents of the database.
     */
    public void save() throws IOException {
        StringBuilder buffer = new StringBuilder();

        for (String key : keySet()) {
            buffer.append(key);
            buffer.append("=");
            buffer.append(getString(key).get());
            buffer.append("\n");
        }

        Files.write(getFile().toPath(), buffer.toString().getBytes(StandardCharsets.UTF_8));
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
