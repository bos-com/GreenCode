# Contributing to GreenCode

Thank you for contributing to GreenCode.

## Workflow

1. Fork the repository.
2. Create/select an issue that describes the problem clearly.
3. Create a branch from `main`:
   - `docs/<short-description>`
   - `fix/<short-description>`
   - `feat/<short-description>`
4. Make focused changes that address one issue or one logical unit of work.
5. Run local checks relevant to your change.
6. Open a pull request and reference the issue (`Resolves #<issue_number>`).

## Pull Request Guidelines

- Use a concise, descriptive title.
- Explain what changed and why it is useful.
- Include test/verification steps.
- Keep PR scope small and reviewable.

## Commit Message Guidance

Prefer conventional-style prefixes:
- `docs: ...`
- `fix: ...`
- `feat: ...`
- `refactor: ...`

## Code and Documentation Standards

- Follow existing project structure and naming style.
- Update README/docs when setup or behavior changes.
- Do not introduce breaking changes without justification and migration notes.

## Security and Compliance

- Never commit secrets (passwords, API keys, tokens).
- Use sample environment files for configuration examples.
- Preserve license/copyright notices where applicable.
