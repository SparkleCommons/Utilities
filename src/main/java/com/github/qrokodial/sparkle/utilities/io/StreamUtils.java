package com.github.qrokodial.sparkle.utilities.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    /**
     * Reads an input stream fully and converts it to a string using a given encoding.
     *
     * @param inputStream
     * @param encoding
     * @return the string representation of the content sent via the input stream
     * @throws IOException
     */
    public static String readFully(InputStream inputStream, String encoding) throws IOException {
        return new String(readFully(inputStream), encoding);
    }

    private static byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        return outputStream.toByteArray();
    }
}
