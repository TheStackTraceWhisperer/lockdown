package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GreeterTest {

    private final Greeter greeter = new Greeter();

    @Test
    void greetShouldReturnGreeting() {
        String result = greeter.greet("Chloe");
        assertThat(result).isEqualTo("Hello, Chloe!");
    }

    @Test
    void greetShouldReturnGreetingForMultipleNames() {
        String result = greeter.greet("Chloe and Emma");
        assertThat(result).isEqualTo("Hello, Chloe and Emma!");
    }

    @Test
    @SuppressWarnings("NullAway")
    void greetShouldThrowExceptionForNullName() {
        assertThatThrownBy(() -> greeter.greet(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Name cannot be null");
    }
}
