package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteConverter implements Converter<Byte> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Byte object, DataOutputStream out) throws IOException {
        out.writeByte(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte deserialize(DataInputStream in) throws IOException {
        return in.readByte();
    }
}
