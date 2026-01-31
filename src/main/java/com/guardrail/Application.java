package com.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application class implementing the main application logic.
 */
public final class Application implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private final Greeter greeter;

    public Application(Greeter greeter) {
        this.greeter = greeter;
    }

    @Override
    public void run() {
        LOGGER.info("System starting...");
        System.out.println(greeter.greet("Chloe and Emma"));
    }
}
