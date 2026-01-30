package com.guardrail;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
    @SuppressWarnings("NullAway")
    void greetShouldThrowExceptionForNullName() {
        assertThatThrownBy(() -> Main.greet(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Name cannot be null");
    }

    @Test
    void mainShouldExecuteWithoutException() {
        // Capture System.out to verify main() output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
            Main.main(new String[]{});
            String output = outContent.toString(StandardCharsets.UTF_8);
            // Verify that println was called - the direct println line starts with "Hello"
            // (logger output has timestamp prefix)
            boolean hasPrintlnOutput = output.lines()
                .anyMatch(line -> line.trim().equals("Hello, Chloe and Emma!"));
            assertThat(hasPrintlnOutput).isTrue();
        } finally {
            System.setOut(originalOut);
        }
    }
}
