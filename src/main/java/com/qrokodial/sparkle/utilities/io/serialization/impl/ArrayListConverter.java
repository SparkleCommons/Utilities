package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.CallbackConverter;
import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ArrayListConverter extends CallbackConverter<List> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(List object, DataOutputStream out) throws Exception {
        out.writeInt(object.size());

        for (Object element : object) {
            Converter converter = getSerializer().assertConverter(element.getClass());

            out.writeUTF(element.getClass().getName());
            converter.serialize(element, out);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List deserialize(DataInputStream in) throws Exception {
        int size = in.readInt();
        List<?> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Class<?> type = Class.forName(in.readUTF());
            Converter converter = getSerializer().assertConverter(type);

            converter.deserialize(in);
        }

        return list;
    }
}
