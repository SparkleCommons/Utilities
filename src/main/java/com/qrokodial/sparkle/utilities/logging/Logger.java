package com.qrokodial.sparkle.utilities.logging;

import com.qrokodial.sparkle.utilities.characters.StringUtils;
import com.qrokodial.sparkle.utilities.time.DateUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;

public class Logger {
    private List<OutputStream> streams;
    private Charset charset;
    private String id;
    private Level minimumLevel;

    /**
     * Constructs the class.
     *
     * @param streams      the output streams to push logging output to
     * @param charset      the charset in which to use for output
     * @param id           an ID that can be used to identify this logger
     * @param minimumLevel the minimum logging level that gets outputted
     */
    public Logger(OutputStream[] streams, Charset charset, String id, Level minimumLevel) {
        this.streams = new ArrayList<>();
        addStreams(streams);

        this.charset = charset;
        this.id = id;
        this.minimumLevel = minimumLevel;
    }

    /**
     * Constructs the class.  Sends output solely to {@link System#out}.
     *
     * @param id           an ID that can be used to identify this logger
     * @param minimumLevel the minimum logging level that gets outputted
     */
    public Logger(String id, Level minimumLevel) {
        this(new OutputStream[] { System.out }, StandardCharsets.UTF_8, id, minimumLevel);
    }

    /**
     * Constructs the class.  Sends output solely to {@link System#out}.
     *
     * @param minimumLevel the minimum logging level that gets outputted
     */
    public Logger(Level minimumLevel) {
        this(null, minimumLevel);
    }

    /**
     * Constructs the class.  Sends output solely to {@link System#out}.
     */
    public Logger() {
        this(Level.ALL);
    }

    /**
     * @return the output streams that this logger logs to
     */
    public List<OutputStream> getStreams() {
        return Collections.unmodifiableList(streams);
    }

    /**
     * Subscribes output streams to future logging events.
     *
     * @param streams the subscribing output streams
     */
    public void addStreams(OutputStream... streams) {
        for (OutputStream stream : streams) {
            this.streams.add(stream);
        }
    }

    /**
     * @return the charset used in output
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * @return an {@link Optional} that might contain an ID used to identify this logger
     */
    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * @return the minimum logging level that gets outputted
     */
    public Level getMinimumLevel() {
        return minimumLevel;
    }

    /**
     * Logs a message as {@link Level#FINEST}.
     *
     * @param message the message to log
     */
    public void finest(Object... message) {
        println(Level.FINEST, message);
    }

    /**
     * Logs a message as {@link Level#FINER}.
     *
     * @param message the message to log
     */
    public void finer(Object... message) {
        println(Level.FINER, message);
    }

    /**
     * Logs a message as {@link Level#FINE}.
     *
     * @param message the message to log
     */
    public void fine(Object... message) {
        println(Level.FINE, message);
    }

    /**
     * Logs a message as {@link Level#CONFIG}.
     *
     * @param message the message to log
     */
    public void config(Object... message) {
        println(Level.CONFIG, message);
    }

    /**
     * Logs a message as {@link Level#INFO}.
     *
     * @param message the message to log
     */
    public void info(Object... message) {
        println(Level.INFO, message);
    }

    /**
     * Logs a message as {@link Level#WARNING}.
     *
     * @param message the message to log
     */
    public void warning(Object... message) {
        println(Level.WARNING, message);
    }

    /**
     * Logs a message as {@link Level#SEVERE}.
     *
     * @param message the message to log
     */
    public void severe(Object... message) {
        println(Level.SEVERE, message);
    }

    /**
     * Logs a message followed by a newline character "\n".
     *
     * @param level   the logging level that this message should be logged at
     * @param message the message to log
     */
    public void println(Level level, Object... message) {
        Object[] newMessage = new Object[message.length + 1];
        System.arraycopy(message, 0, newMessage, 0, message.length);
        newMessage[message.length] = "\n";

        print(level, newMessage);
    }

    /**
     * Logs a message without appending a newline character.
     *
     * @param level   the logging level that this message should be logged at
     * @param message the message to log
     */
    public void print(Level level, Object... message) {
        if (level.intValue() >= getMinimumLevel().intValue()) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(DateUtils.TIMESTAMP.format(new Date()));

            getId().ifPresent(id -> {
                buffer.append(" (");
                buffer.append(id);
                buffer.append(")");
            });

            buffer.append(" [");
            buffer.append(level.getLocalizedName());
            buffer.append("] ");

            buffer.append(StringUtils.combine(message));

            byte[] payload = buffer.toString().getBytes(getCharset());

            getStreams().forEach(stream -> {
                int offset = 0;

                try {
                    while (payload.length > offset) {
                        int length = Math.min(1024, payload.length - offset);

                        stream.write(payload, offset, length);
                        stream.flush();

                        offset += length;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
