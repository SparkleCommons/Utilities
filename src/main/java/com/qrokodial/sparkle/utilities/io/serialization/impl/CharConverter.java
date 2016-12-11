package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CharConverter implements Converter<Character> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Character object, DataOutputStream out) throws IOException {
        out.writeChar(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character deserialize(DataInputStream in) throws IOException {
        return in.readChar();
    }
}
