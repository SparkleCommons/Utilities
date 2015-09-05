package com.github.qrokodial.sparkle.utilities.cleaning;

public interface Destroyable {
    /**
     * Signals that the class is being destroyed and should be cleaned up.
     */
    void destroy();
}
