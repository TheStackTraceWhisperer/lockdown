package com.guardrail;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

@AnalyzeClasses(packages = "com.guardrail")
final class ArchitectureTest {
    
    private ArchitectureTest() { }
    
    @ArchTest
    static final ArchRule NO_STDOUT = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
}
