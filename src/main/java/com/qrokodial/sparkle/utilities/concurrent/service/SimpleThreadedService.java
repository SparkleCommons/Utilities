package com.qrokodial.sparkle.utilities.concurrent.service;

public abstract class SimpleThreadedService extends SimpleService {
    @Override
    public void start() {
        new Thread(super::start).start();
    }
}
