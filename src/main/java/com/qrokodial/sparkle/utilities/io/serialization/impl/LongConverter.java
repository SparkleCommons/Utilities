package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LongConverter implements Converter<Long> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Long object, DataOutputStream out) throws IOException {
        out.writeLong(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long deserialize(DataInputStream in) throws IOException {
        return in.readLong();
    }
}
