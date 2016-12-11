package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerConverter implements Converter<Integer> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Integer object, DataOutputStream out) throws IOException {
        out.writeInt(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deserialize(DataInputStream in) throws IOException {
        return in.readInt();
    }
}
