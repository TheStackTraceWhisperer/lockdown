package com.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() { }

    public static void main(String[] args) {
        LOGGER.info("System starting...");
        Greeter greeter = new Greeter();
        System.out.println(greeter.greet("Chloe and Emma"));
    }
}
