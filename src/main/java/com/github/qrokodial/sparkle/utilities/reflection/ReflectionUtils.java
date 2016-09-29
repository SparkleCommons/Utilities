package com.github.qrokodial.sparkle.utilities.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    /**
     * Gets a list of all methods within a class that are marked with an annotation.
     *
     * @param type       the class to search
     * @param annotation the annotation to match
     * @return the relevant list of methods
     */
    public static List<Method> getMethodsWithAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
        List<Method> methods = new ArrayList<>();

        Class<?> clazz = type;
        while (clazz != Object.class) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }

            clazz = clazz.getSuperclass();
        }

        return methods;
    }
}
