# GitHub Copilot Instructions for lockdown Repository

## Project Overview
This is a Java 21 Maven project demonstrating a "zero-trust" build pipeline with comprehensive validation guardrails. The project emphasizes code quality, security, and maintainability through multiple layers of automated checks.

## Architecture Principles

### Code Organization
- **Main.java**: Keep absolutely minimal - only entry point logic (instantiation and delegation)
- **Business Logic**: Separate into dedicated service classes (e.g., Greeter.java)
- **Tests**: Organize by concern:
  - Unit tests for business logic (*Test.java)
  - Integration tests (*IT.java or *ITCase.java)
  - Architecture tests (ArchitectureTest.java)

### Testing Guidelines
- **DO NOT** test System.out or logging output - these are test fallacies
- **DO** test business logic behavior and return values
- **DO** use simple exception assertions for entry point methods (e.g., `assertThatCode().doesNotThrowAnyException()`)
- **DO** aim for 100% coverage on business logic classes
- **DO** exclude entry points (main methods) from mutation testing

## Build Pipeline

### 11-Layer Validation Cascade
The build uses Maven with the following plugins (all must pass):

1. **Maven Enforcer** - Java version and dependency rules
2. **Checkstyle** - Code style and formatting
3. **Error Prone** - Semantic bug detection  
4. **NullAway** - Null-safety enforcement
5. **Modernizer** - Legacy API detection
6. **Surefire** - Unit tests
7. **Failsafe** - Integration tests
8. **ArchUnit** - Architecture rules
9. **JaCoCo** - Code coverage (80% minimum on business logic)
10. **Pitest** - Mutation testing (80% minimum)
11. **PMD/SpotBugs** - Static analysis

### Build Commands
- Standard build: `mvn clean verify`
- With wrapper: `./mvnw clean verify`
- Quick test: `mvn test`

## Code Quality Standards

### Java 21 Features
- Use modern Java 21 constructs (records, pattern matching, sequenced collections)
- Avoid deprecated APIs (Modernizer will catch these)

### Null Safety
- All packages must have `@ParametersAreNonnullByDefault` via package-info.java
- NullAway enforces compile-time null checks
- Use `Objects.requireNonNull()` for runtime validation

### Error Prone Configuration
- Combines Error Prone and NullAway in single compiler plugin argument
- Requires Java module exports for Java 21 compatibility
- All compiler warnings treated as errors (`-Werror`)

## Common Issues and Solutions

### Compilation Errors
- **Invalid flag: -Xep:NullAway:ERROR**: Ensure Error Prone arguments are combined in a single `<arg>` tag
- **Module access errors**: Verify all `-J--add-exports` and `-J--add-opens` flags are present for Java 21

### Test Failures
- **ArchUnit violations**: Update predicates to exclude test classes properly (Test, IT, ITCase patterns)
- **Coverage failures**: Ensure business logic is in separate classes from Main, not excluded from coverage
- **Mutation failures**: Exclude main methods and logger calls from mutation testing

### Dependency Management
- Always check for CVEs when adding/updating dependencies
- Use latest stable versions
- Prefer Maven Central artifacts

## File Organization

### Source Structure
```
src/main/java/com/guardrail/
  ├── package-info.java          # NullAway configuration
  ├── Main.java                  # Minimal entry point
  └── [ServiceClass].java        # Business logic classes

src/test/java/com/guardrail/
  ├── [ServiceClass]Test.java    # Unit tests
  ├── MainIT.java                # Integration tests  
  └── ArchitectureTest.java      # Architecture rules
```

### Configuration Files
- `pom.xml` - Maven build configuration
- `checkstyle.xml` - Code style rules
- `pmd-ruleset.xml` - PMD rules
- `logback.xml` - Logging configuration
- `.gitignore` - Excludes target/ and build artifacts
- `.mvn/wrapper/` - Maven wrapper files

## Git Workflow

### Commits
- Keep commits focused and atomic
- Build must pass after each commit
- Target folder is gitignored

### Pull Requests
- All 11 validation gates must pass
- CI workflow runs automatically via GitHub Actions
- Test results uploaded as artifacts

## Dependencies

### Security
- Run `mvn dependency-check:check` for CVE scanning (if plugin added)
- Update AssertJ and other dependencies to latest secure versions
- No banned dependencies (JUnit 4, Log4j 1.x, etc.)

### Core Libraries
- SLF4J 2.x for logging
- Logback for logging implementation
- JUnit 5 for testing
- AssertJ for fluent assertions
- ArchUnit for architecture testing

## When Making Changes

1. **Refactoring**: Keep Main minimal, move logic to service classes
2. **Adding features**: Create new service classes, add corresponding tests
3. **Updating dependencies**: Check for CVEs, update pom.xml
4. **Configuration changes**: Test with `mvn clean verify` before committing
5. **Test modifications**: Never test logging/System.out - test behavior only

## Maven Wrapper
- Use `./mvnw` instead of `mvn` for reproducible builds
- Wrapper version: 3.9.9
- No local Maven installation required
