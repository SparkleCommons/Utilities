package com.github.qrokodial.sparkle.utilities.io.properties;

import com.github.qrokodial.sparkle.utilities.casting.CastableStringMap;
import com.github.qrokodial.sparkle.utilities.casting.ReadableCastableDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class ReadablePropertiesFile implements ReadableCastableDatabase<String> {
    protected CastableStringMap map;
    private File file;

    /**
     * Instantiates the class.
     *
     * @param file
     */
    public ReadablePropertiesFile(File file) throws IOException {
        map = new CastableStringMap();
        setFile(file);
        reload();
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
     * Reloads the contents of the file.
     */
    public void reload() throws IOException {
        map.clear();

        if (getFile().exists() && getFile().isFile()) {
            String contents = new String(Files.readAllBytes(getFile().toPath()));
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
