package com.guardrail;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.guardrail")
final class ArchitectureTest {
    
    private ArchitectureTest() { }
    
    // Original test - no System.out except in Main and tests
    @ArchTest
    static void noSystemOutExceptMainAndTests(JavaClasses classes) {
        noClasses()
            .that().doNotHaveSimpleName("Main")
            .and().haveNameNotMatching(".*(Test|IT|ITCase)$")
            .should().accessClassesThat().haveFullyQualifiedName("java.io.PrintStream")
            .check(classes);
    }
    
    // Best Practice: No access to standard streams in production code (except Main)
    @ArchTest
    static final ArchRule NO_STANDARD_STREAMS_IN_PRODUCTION_CODE = 
        NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
            .because("Use a proper logging framework instead")
            .allowEmptyShould(true);
    
    // Best Practice: No generic exceptions
    @ArchTest
    static final ArchRule NO_GENERIC_EXCEPTIONS = 
        NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
            .because("Use specific exceptions for better error handling");
    
    // Best Practice: No field injection
    @ArchTest
    static final ArchRule NO_FIELD_INJECTION = 
        NO_CLASSES_SHOULD_USE_FIELD_INJECTION
            .because("Use constructor injection for better testability");
    
    // Best Practice: No java.util.logging
    @ArchTest
    static final ArchRule NO_JAVA_UTIL_LOGGING = 
        NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .because("Use SLF4J for logging abstraction");
    
    // Best Practice: Classes should follow naming conventions
    @ArchTest
    static void classesShouldNotEndWithImpl(JavaClasses classes) {
        noClasses()
            .should().haveSimpleNameEndingWith("Impl")
            .because("Implementation suffix is redundant - use meaningful names")
            .check(classes);
    }
    
    // Best Practice: Test classes should have proper naming
    @ArchTest
    static void testClassesShouldBeProperlyNamed(JavaClasses classes) {
        classes()
            .that().haveSimpleNameEndingWith("Test")
            .should().haveSimpleNameNotEndingWith("Tests")
            .because("Test classes should end with 'Test', not 'Tests'")
            .check(classes);
    }
    
    // Best Practice: Loggers should be private static final
    @ArchTest
    static void loggersShouldBePrivateStaticFinal(JavaClasses classes) {
        fields()
            .that().haveRawType("org.slf4j.Logger")
            .should().bePrivate()
            .andShould().beStatic()
            .andShould().beFinal()
            .because("Loggers should be private static final by convention")
            .check(classes);
    }
    
    // Best Practice: No cycles in package dependencies
    @ArchTest
    static void packagesShouldBeFreeOfCycles(JavaClasses classes) {
        slices()
            .matching("com.guardrail.(*)..")
            .should().beFreeOfCycles()
            .allowEmptyShould(true)
            .check(classes);
    }
}
