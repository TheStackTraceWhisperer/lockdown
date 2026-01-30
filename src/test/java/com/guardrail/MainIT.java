package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for Greeter class.
 * Note: This is a simple integration test without containers.
 * Testcontainers annotation removed as no containers are used.
 */
class MainIT {

    private final Greeter greeter = new Greeter();

    @Test
    void integrationTestGreetShouldWork() {
        // Simple integration test to validate greeting functionality
        String result = greeter.greet("Integration Test");
        assertThat(result).isEqualTo("Hello, Integration Test!");
    }
}
