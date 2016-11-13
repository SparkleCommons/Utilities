package com.qrokodial.sparkle.utilities.concurrent.pool;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A {@link Pool} implementation that is backed by a {@link ConcurrentLinkedQueue}.  The only other noteworthy thing is
 * that the pool does not block during the {@link #fetch()} method - if the pool is empty, a new element is allocated.
 */
public abstract class ResponsivePool<T> implements Pool<T> {
    private Queue<T> queue;

    /**
     * Constructs the class.
     */
    public ResponsivePool() {
        queue = new ConcurrentLinkedQueue<>();
    }

    /**
     * @return the underlying queue
     */
    protected Queue<T> getQueue() {
        return queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return getQueue().size();
    }

    /**
     * Fetches an element from the pool.  If no element is found, one is allocated.
     *
     * @return an element ready for use
     */
    @Override
    public T fetch() {
        try {
            return getQueue().remove();
        } catch (NoSuchElementException e) {
            return createNew();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T element) {
        queue.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void allocate(int amount) {
        for (int i = 0; i < amount; i++) {
            add(createNew());
        }
    }

    public abstract T createNew();
}
