package com.qrokodial.sparkle.utilities.concurrent.service;

public interface Service {
    /**
     * @return whether or not the service is running/active
     */
    boolean isRunning();

    /**
     * Starts the service if it is not already running.
     */
    void start();

    /**
     * Stops the service if it is currently running.
     */
    void stop();
}
