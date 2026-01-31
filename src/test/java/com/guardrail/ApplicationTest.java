package com.guardrail;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;

class ApplicationTest {

    @Test
    void runShouldNotThrowException() {
        Greeter greeter = new Greeter();
        Application app = new Application(greeter);
        
        assertThatCode(app::run)
            .doesNotThrowAnyException();
    }
}
