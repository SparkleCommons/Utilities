package com.github.qrokodial.sparkle.utilities.concurrent;

public interface Pool<T> {
    /**
     * @return the amount of unallocated elements held by the pool
     */
    int size();

    /**
     * @return an element from the pool
     */
    T fetch();

    /**
     * Adds an element to the pool to be allocated later.
     *
     * @param element the element to add=
     */
    void add(T element);

    /**
     * Allocates a certain amount of new elements in the pool to be used later.
     *
     * @param amount the amount of elements to allocate
     */
    void allocate(int amount);
}
