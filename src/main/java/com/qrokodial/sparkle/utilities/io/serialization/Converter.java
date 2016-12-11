package com.qrokodial.sparkle.utilities.io.serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Converter<T> {
    /**
     * Serializes an object to an output stream.
     *
     * @param object the object to serialize
     * @param out    the output stream
     *
     * @throws Exception if there was an error during the serialization process
     */
    void serialize(T object, DataOutputStream out) throws Exception;

    /**
     * Deserializes an object from an input stream.
     *
     * @param in the input stream
     *
     * @return the deserialized object
     * @throws Exception if there was an error during the deserialization process
     */
    T deserialize(DataInputStream in) throws Exception;
}
