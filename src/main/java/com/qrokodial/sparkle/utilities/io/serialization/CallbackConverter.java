package com.qrokodial.sparkle.utilities.io.serialization;

public abstract class CallbackConverter<T> implements Converter<T> {
    private Serializer serializer;

    /**
     * Is used by the serializer to set the callback instance.
     *
     * @param serializer the callback serializer
     */
    protected final void _initialize(Serializer serializer) {
        this.serializer = serializer;
    }

    /**
     * @return the callback serializer
     */
    public Serializer getSerializer() {
        return serializer;
    }
}
