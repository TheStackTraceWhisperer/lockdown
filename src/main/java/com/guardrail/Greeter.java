package com.guardrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

/**
 * Service class for generating greetings.
 */
public final class Greeter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeter.class);

    /**
     * Greets the user.
     * @param name Name to greet (NullAway enforces non-null)
     * @return Greeting string
     */
    public String greet(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        String greeting = "Hello, " + name + "!";
        LOGGER.info("Generated greeting: {}", greeting);
        return greeting;
    }
}
