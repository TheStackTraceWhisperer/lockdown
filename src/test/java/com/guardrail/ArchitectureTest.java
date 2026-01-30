package com.guardrail;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.guardrail")
final class ArchitectureTest {
    
    private ArchitectureTest() { }
    
    @ArchTest
    static void noSystemOutExceptMainAndTests(JavaClasses classes) {
        noClasses()
            .that().doNotHaveSimpleName("Main")
            .and().haveSimpleNameNotEndingWith("Test")
            .should().accessClassesThat().haveFullyQualifiedName("java.io.PrintStream")
            .check(classes);
    }
}
