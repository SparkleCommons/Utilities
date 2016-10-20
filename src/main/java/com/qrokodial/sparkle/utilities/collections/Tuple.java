package com.qrokodial.sparkle.utilities.collections;

public class Tuple<A, B> {
    public final A first;
    public final B second;

    /**
     * Instantiates the class.
     *
     * @param first  the first component of the tuple
     * @param second the second component of the tuple
     */
    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first component of the tuple
     */
    public final A getFirst() {
        return first;
    }

    /**
     * @return the second component of the tuple
     */
    public final B getSecond() {
        return second;
    }
}
