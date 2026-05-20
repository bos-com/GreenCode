# Contributing to GreenCode

Thank you for contributing to GreenCode. The project supports environmental and
sustainability workflows, so contributions should be clear, maintainable, and
easy for new developers to verify.

## Contribution Types

Useful contributions include:

- Bug fixes and feature enhancements.
- Documentation improvements.
- API examples and setup guidance.
- UI/UX suggestions for future frontend work.
- Governance and sustainability recommendations.
- Tests that improve confidence in backend behavior.

## Workflow

1. Pick an existing issue or open one before making a large change.
2. Fork the repository and create a branch from `main`.
3. Use a descriptive branch name:
   - `fix/user-validation`
   - `feat/project-search`
   - `docs/local-setup`
4. Keep the change focused and reviewable.
5. Link the issue in the pull request description using `Closes #123`.

## Development Checklist

Before opening a pull request:

- Run `mvn test` when changing Java code.
- Update docs when setup, APIs, or configuration behavior changes.
- Avoid committing secrets, generated files, build output, or local `.env`
  files.
- Keep examples aligned with `env.example` and existing project structure.

## Commit Style

Use short, descriptive commit messages. Conventional prefixes are encouraged:

- `docs: improve local setup guide`
- `fix: validate project rating range`
- `feat: add project search endpoint`
- `test: cover user service validation`

## Review Expectations

Maintainers should be able to understand:

- What changed.
- Why the change is needed.
- How it was tested.
- Which issue the pull request resolves.

Documentation-only pull requests should still include a brief testing note, such
as confirming Markdown links and headings render correctly.
