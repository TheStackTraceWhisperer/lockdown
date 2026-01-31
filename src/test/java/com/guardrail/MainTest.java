package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

class MainTest {

    @Test
    void mainShouldNotThrowException() {
        assertThatCode(() -> Main.main(new String[]{}))
            .doesNotThrowAnyException();
    }
}
