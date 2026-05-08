# Testing Guide

This guide explains how contributors can run tests before submitting changes to GreenCode.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Run All Tests

```bash
mvn test
Run Tests with Clean Build
mvn clean test
Build Without Running Tests
mvn clean install -DskipTests
Recommended Before Pull Request

Before opening a pull request, run:

mvn clean test
Notes
Fix failing tests before submitting a PR.
Add tests when introducing new features.
Mention testing results in the pull request description.

Commit message:
```text
Add testing guide for contributors
