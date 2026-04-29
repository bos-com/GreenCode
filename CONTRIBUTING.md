# Contributing to GreenCode

Thanks for helping improve GreenCode. This guide keeps contributions focused,
reviewable, and easy for maintainers to merge.

## Before You Start

1. Check GitHub Issues for an existing report or proposal.
2. Open a new issue if the work is not already tracked.
3. Keep each pull request focused on one bug fix, feature, documentation update,
   or governance improvement.

## Development Workflow

1. Fork the repository and clone your fork.
2. Create a branch from `main`:

   ```bash
   git checkout -b docs/update-setup-guide
   ```

3. Install prerequisites listed in the README.
4. Make the smallest complete change that resolves the issue.
5. Run the relevant checks:

   ```bash
   mvn test
   ```

6. Commit with a conventional commit prefix such as `fix:`, `feat:`, `docs:`,
   or `style:`.
7. Open a pull request and include the issue reference, testing notes, and any
   deployment or configuration impact.

## Pull Request Checklist

- The PR links the issue it resolves.
- The change is scoped to one reviewable topic.
- Documentation is updated when behavior or setup steps change.
- Relevant tests pass locally, or skipped checks are explained.
- No secrets, local credentials, build artifacts, or generated logs are included.

## Code Expectations

- Follow the existing Spring Boot package structure.
- Prefer service-layer business rules over controller-only logic.
- Keep API responses free of sensitive fields such as passwords.
- Add or update tests when changing controllers, services, repositories, or
  validation behavior.
