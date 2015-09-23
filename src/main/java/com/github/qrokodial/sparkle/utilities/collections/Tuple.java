package com.github.qrokodial.sparkle.utilities.collections;

public class Tuple<X, Y> {
    public final X x;
    public final Y y;

    /**
     * Instantiates the class.
     *
     * @param x the X component of the tuple
     * @param y the Y component of the tuple
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the X component of the tuple
     */
    public final X getX() {
        return x;
    }

    /**
     * @return the Y component of the tuple
     */
    public final Y getY() {
        return y;
    }
}
