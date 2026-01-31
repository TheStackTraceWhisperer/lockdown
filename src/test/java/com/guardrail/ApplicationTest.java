package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

class ApplicationTest {

    @Test
    void runShouldNotThrowException() {
        Application app = new Application();
        
        assertThatCode(app::run)
            .doesNotThrowAnyException();
    }
}
