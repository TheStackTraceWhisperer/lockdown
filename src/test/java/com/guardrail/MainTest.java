package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MainTest {

    @Test
    void greetShouldReturnGreeting() {
        String result = Main.greet("Chloe");
        assertThat(result).isEqualTo("Hello, Chloe!");
    }

    @Test
    void greetShouldReturnGreetingForMultipleNames() {
        String result = Main.greet("Chloe and Emma");
        assertThat(result).isEqualTo("Hello, Chloe and Emma!");
    }

    @Test
    void greetShouldThrowExceptionForNullName() {
        assertThatThrownBy(() -> Main.greet(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Name cannot be null");
    }

    @Test
    void mainShouldExecuteWithoutException() {
        // This test ensures main() can be called without exception
        Main.main(new String[]{});
    }
}
