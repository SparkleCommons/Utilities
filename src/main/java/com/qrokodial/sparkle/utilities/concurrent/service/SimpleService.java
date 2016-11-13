package com.qrokodial.sparkle.utilities.concurrent.service;

public abstract class SimpleService implements Service {
    private boolean running;

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void start() {
        running = true;

        onStart();
        while (isRunning()) {
            onTick();
        }
        onStop();
    }

    @Override
    public void stop() {
        running = false;
    }

    protected abstract void onStart();

    protected abstract void onTick();

    protected abstract void onStop();
}
