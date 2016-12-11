package com.qrokodial.sparkle.utilities.io.serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * This is a placeholder class for {@link Serialize#converter()}.
 */
public class DefaultConverter implements Converter<Object> {
    /**
     * This will never actually be executed and serves only as a placeholder.
     */
    @Override
    public void serialize(Object object, DataOutputStream out) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * This will never actually be executed and serves only as a placeholder.
     */
    @Override
    public Object deserialize(DataInputStream in) throws Exception {
        throw new UnsupportedOperationException();
    }
}
