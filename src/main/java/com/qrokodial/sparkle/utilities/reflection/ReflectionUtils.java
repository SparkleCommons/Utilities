package com.qrokodial.sparkle.utilities.reflection;

import com.qrokodial.sparkle.utilities.collections.ArrayUtils;
import com.qrokodial.sparkle.utilities.collections.Tuple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReflectionUtils {
    /**
     * Gets a list of all fields within a class that are marked with an annotation.
     *
     * @param type       the class to search
     * @param annotation the annotation to match
     * @param checkSuper whether or not to check all of the class' supertypes
     * @return the relevant list of methods
     */
    public static List<Field> getFieldsWithAnnotation(Class<?> type, Class<? extends Annotation> annotation, boolean checkSuper) {
        List<Field> fields = new ArrayList<>();

        Class<?> clazz = type;
        while  (clazz != Object.class) {
            for (Field field : type.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    fields.add(field);
                }
            }

            if (!checkSuper) {
                break;
            }

            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * Gets a list of all fields within a class that are marked with an annotation.
     *
     * @param type       the class to search
     * @param annotation the annotation to match
     * @return the relevant list of methods
     */
    public static List<Field> getFieldsWithAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
        return getFieldsWithAnnotation(type, annotation, false);
    }

    /**
     * Gets the type and the value of an object's field.
     *
     * @param object the owner of the field
     * @param field  the field
     * @return a tuple containing the type in the first element and the value in the second
     */
    public static Tuple<Class<?>, Object> getFieldAttributes(Object object, Field field) {
        boolean accessible = field.isAccessible();

        if (!accessible) {
            field.setAccessible(true);
        }

        Class<?> type = field.getType();
        Object value = null;

        try {
            value = field.get(object);
        } catch (IllegalAccessException shouldNeverHappen) {
            shouldNeverHappen.printStackTrace();
        }

        if (!accessible) {
            field.setAccessible(false);
        }

        return new Tuple<>(type, value);
    }

    /**
     * Sets the value of a field for a given object, regardless if the field is accessible.
     *
     * @param object the given object
     * @param field  the field to set
     * @param value  the value to give the field
     */
    public static void setField(Object object, Field field, Object value) {
        boolean accessible = field.isAccessible();

        if (!accessible) {
            field.setAccessible(true);
        }

        try {
            field.set(object, value);
        } catch (IllegalAccessException shouldNeverHappen) {
            shouldNeverHappen.printStackTrace();
        }

        if (!accessible) {
            field.setAccessible(false);
        }
    }

    /**
     * Gets a list of all methods within a class that are marked with an annotation.
     *
     * @param type       the class to search
     * @param annotation the annotation to match
     * @param checkSuper whether or not to check all of the class' supertypes
     * @return the relevant list of methods
     */
    public static List<Method> getMethodsWithAnnotation(Class<?> type, Class<? extends Annotation> annotation, boolean checkSuper) {
        List<Method> methods = new ArrayList<>();

        Class<?> clazz = type;
        while (clazz != Object.class) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }

            if (!checkSuper) {
                break;
            }

            clazz = clazz.getSuperclass();
        }

        return methods;
    }

    /**
     * Gets a list of all methods within a class that are marked with an annotation.
     *
     * @param type       the class to search
     * @param annotation the annotation to match
     * @return the relevant list of methods
     */
    public static List<Method> getMethodsWithAnnotation(Class<?> type, Class<? extends Annotation> annotation) {
        return getMethodsWithAnnotation(type, annotation, false);
    }

    /**
     * Attempts to get the constructor of a class that fulfills the requirements of the provided parameters.
     *
     * @param constructorClass the class that owns the constructors
     * @param parameters       the parameters that the constructor must satisfy
     * @return an {@link Optional} containing the relevant constructor, if one exists
     */
    public static <T> Optional<Constructor<T>> getConstructorMatching(Class<T> constructorClass, Object... parameters) {
        Class[] classes = ArrayUtils.getTypes(parameters);

        for (Constructor constructor : constructorClass.getDeclaredConstructors()) {
            Parameter[] cParams = constructor.getParameters();

            if (cParams.length == 0 && parameters.length == 0) {
                return Optional.of((Constructor<T>)constructor);
            }

            if (classes.length != cParams.length && !constructor.isVarArgs()) {
                continue;
            }

            for (int i = 0; i < classes.length; i++) {
                if (!cParams[i].getType().isAssignableFrom(classes[i])) {
                    break;
                }

                if (cParams[i].isVarArgs() && !isAssignableFromClasses(cParams[i].getType(), classes, i, classes.length - 1)) {
                    break;
                }

                if (i + 1 == cParams.length) {
                    return Optional.of((Constructor<T>)constructor);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Checks to see if a main class is assignable from multiple other classes.
     *
     * @param mainClass  the main class
     * @param classes    the other classes
     * @param startIndex the index of the array of the other classes to check
     * @param endIndex   the end index of the array of the other classes to check
     * @return true of the main class is assignable from all of the relevant other classes, false otherwise
     */
    public static boolean isAssignableFromClasses(Class<?> mainClass, Class<?>[] classes, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (!mainClass.isAssignableFrom(classes[i])) {
                return false;
            }
        }

        return true;
    }
}
