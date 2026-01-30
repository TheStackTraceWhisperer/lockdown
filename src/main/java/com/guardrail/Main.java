package com.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() { }

    /**
     * Greets the user.
     * @param name Name to greet (NullAway enforces non-null)
     * @return Greeting string
     */
    public static String greet(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        String greeting = "Hello, " + name + "!";
        LOGGER.info("Generated greeting: {}", greeting);
        return greeting;
    }

    public static void main(String[] args) {
        // Greet Chloe and Emma
        LOGGER.info("System starting...");
        System.out.println(greet("Chloe and Emma"));
    }
}
