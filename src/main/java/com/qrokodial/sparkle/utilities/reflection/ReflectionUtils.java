package com.qrokodial.sparkle.utilities.reflection;

import com.qrokodial.sparkle.utilities.collections.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
