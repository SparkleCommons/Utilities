package com.qrokodial.sparkle.utilities.io.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Serialize {
    /**
     * Used to specify a custom converter to use during serialization/deserialization.  The default,
     * {@link DefaultConverter}, is used in order to indicate that the appropriate converter should be guessed.
     *
     * @return the converter to use for serialization/deserialization
     */
    Class<? extends Converter> converter() default DefaultConverter.class;
}
