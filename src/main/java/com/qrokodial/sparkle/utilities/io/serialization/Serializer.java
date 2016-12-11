package com.qrokodial.sparkle.utilities.io.serialization;

import com.qrokodial.sparkle.utilities.collections.Tuple;
import com.qrokodial.sparkle.utilities.io.serialization.impl.*;
import com.qrokodial.sparkle.utilities.reflection.ReflectionUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Serializer {
    private static Map<Class<?>, Converter> converterMap = new ConcurrentHashMap<>();

    /**
     * Constructs the class and adds the default converters.
     */
    public Serializer() {
        addConverter(Byte.class, new ByteConverter());
        addConverter(Short.class, new ShortConverter());
        addConverter(Integer.class, new IntegerConverter());
        addConverter(Long.class, new LongConverter());

        addConverter(Character.class, new CharConverter());
        addConverter(String.class, new StringConverter());

        addConverter(Set.class, new HashSetConverter());
        addConverter(List.class, new ArrayListConverter());
        addConverter(Map.class, new HashMapConverter());
    }

    /**
     * @return all of the currently-registered converters
     */
    public Collection<Converter> getConverters() {
        return Collections.unmodifiableCollection(converterMap.values());
    }

    /**
     * Attempts to get a registered converter based on its type.
     *
     * @param forType the type that the converter converts
     *
     * @return an {@link Optional} that contains the relevant registered converter, if one exists
     */
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
        Converter<?> value = converterMap.get(forType);

        if (value == null) {
            return Optional.empty();
        }

        return Optional.of((Converter<T>)value);
    }

    /**
     * Attempts to get a registered converter based on its type.  If a relevant converter doesn't exist, it throws
     *
     * @param forType the type that the converter converts
     *
     * @return the relevant registered converter
     * @throws SerializationException if there are no relevant registered converters
     */
    public <T> Converter<T> assertConverter(Class<T> forType) throws SerializationException {
        return getConverter(forType).orElseThrow(() -> new SerializationException("The type \"" + forType + "\" does not have a converter"));
    }

    /**
     * Registers a converter with this serializer.
     *
     * @param forType   the type that the converter converts
     * @param converter the converter instance
     */
    public <T> void addConverter(Class<T> forType, Converter<T> converter) {
        if (converter instanceof CallbackConverter) {
            ((CallbackConverter)converter)._initialize(this);
        }

        converterMap.put(forType, converter);
    }

    /**
     * Deregisters a converter with this serializer.
     *
     * @param forType the type that the converter converts
     *
     * @return an {@link Optional} containing the previously-registered converter, if one existed
     */
    public <T> Optional<Converter<T>> removeConverter(Class<T> forType) {
        Converter<?> value = converterMap.remove(forType);

        if (value == null) {
            return Optional.empty();
        }

        return Optional.of((Converter<T>)value);
    }

    /**
     * Serializes an object to an output stream.
     *
     * @param object the object to serialize
     * @param out    the output stream to serialize the object to
     *
     * @throws Exception if there was an error during the serialization process
     */
    public void serialize(Object object, DataOutputStream out) throws Exception {
        List<Field> fields = ReflectionUtils.getFieldsWithAnnotation(object.getClass(), Serialize.class);

        for (Field field : fields) {
            serializeField(object, field, out);
        }
    }

    protected void serializeField(Object object, Field field, DataOutputStream out) throws Exception {
        Tuple<Class<?>, Object> info = ReflectionUtils.getFieldAttributes(object, field);
        Class<?> type = info.getFirst();
        Object value = info.getSecond();

        Serialize annotation = field.getAnnotation(Serialize.class);
        if (!annotation.converter().getClass().equals(DefaultConverter.class)) {
            type = annotation.converter().getClass();
        }

        Converter converter = assertConverter(type);
        converter.serialize(value, out);
    }

    /**
     * Deserializes an object from an input stream.
     *
     * @param type the type of the object to deserialize
     * @param in   the input stream to deserialize the object from
     *
     * @throws Exception if there was an error during the deserialization process
     */
    public <T> T deserialize(Class<T> type, DataInputStream in) throws Exception {
        T instance = type.newInstance();
        List<Field> fields = ReflectionUtils.getFieldsWithAnnotation(type, Serialize.class);

        for (Field field : fields) {
            Object value = deserializeField(field, in);
            ReflectionUtils.setField(instance, field, value);
        }

        return instance;
    }

    protected Object deserializeField(Field field, DataInputStream in) throws Exception {
        Class<?> type = field.getType();

        Serialize annotation = field.getAnnotation(Serialize.class);
        if (!annotation.converter().getClass().equals(DefaultConverter.class)) {
            type = annotation.converter().getClass();
        }

        Converter converter = assertConverter(type);
        return converter.deserialize(in);
    }
}
