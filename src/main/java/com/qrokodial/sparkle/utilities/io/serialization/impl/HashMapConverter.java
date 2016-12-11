package com.qrokodial.sparkle.utilities.io.serialization.impl;

import com.qrokodial.sparkle.utilities.io.serialization.CallbackConverter;
import com.qrokodial.sparkle.utilities.io.serialization.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

public class HashMapConverter extends CallbackConverter<Map> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Map object, DataOutputStream out) throws Exception {
        out.writeInt(object.size());

        for (Object key : object.keySet()) {
            Object value = object.get(key);

            Converter keyConverter = getSerializer().assertConverter(key.getClass());
            Converter valueConverter = getSerializer().assertConverter(value.getClass());

            out.writeUTF(key.getClass().getName());
            out.writeUTF(value.getClass().getName());
            keyConverter.serialize(key, out);
            valueConverter.serialize(value, out);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map deserialize(DataInputStream in) throws Exception {
        int size = in.readInt();
        Map map = new HashMap<>(size);

        for (int i = 0; i < size; i++) {
            Class<?> keyType = Class.forName(in.readUTF());
            Class<?> valueType = Class.forName(in.readUTF());

            Converter keyConverter = getSerializer().assertConverter(keyType);
            Converter valueConverter = getSerializer().assertConverter(valueType);

            Object key = keyConverter.deserialize(in);
            Object value = valueConverter.deserialize(in);

            map.put(key, value);
        }

        return map;
    }
}
