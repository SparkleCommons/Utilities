package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ShortConverter implements Converter<Short> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Short object, DataOutputStream out) throws IOException {
        out.writeShort(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short deserialize(DataInputStream in) throws IOException {
        return in.readShort();
    }
}
