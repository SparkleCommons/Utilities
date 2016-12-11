package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StringConverter implements Converter<String> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(String object, DataOutputStream out) throws IOException {
        out.writeUTF(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deserialize(DataInputStream in) throws IOException {
        return in.readUTF();
    }
}
