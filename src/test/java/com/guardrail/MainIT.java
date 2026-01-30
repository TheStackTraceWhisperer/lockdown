package com.guardrail;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class MainIT {

    @Test
    void integrationTestGreetShouldWork() {
        // Simple integration test to validate greeting functionality
        String result = Main.greet("Integration Test");
        assertThat(result).isEqualTo("Hello, Integration Test!");
    }
}
