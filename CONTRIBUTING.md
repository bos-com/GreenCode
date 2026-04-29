# Contributing to GreenCode

Thank you for contributing to GreenCode. This project combines application
code, deployment configuration, and operational documentation, so good
contributions are not limited to Java changes.

## Before You Start

- Check for an existing issue before starting work.
- Keep each branch focused on one concern.
- Update documentation when behavior, setup steps, or deployment expectations
  change.

## Local Development

### Backend

```bash
git clone https://github.com/bos-com/GreenCode.git
cd GreenCode
mvn test
mvn spring-boot:run
```

### Docker Stack

```bash
cp env.example .env
docker-compose up --build -d
```

## Branch Naming

Use clear, scoped branch names:

- `docs/<topic>` for documentation
- `feat/<topic>` for new features
- `fix/<topic>` for bug fixes
- `chore/<topic>` for maintenance

Examples:

- `docs/readme-setup`
- `fix/health-endpoint-docs`
- `feat/user-audit-log`

## Pull Request Expectations

Each pull request should:

- link the issue it resolves,
- explain the problem and the chosen fix,
- list any commands used for verification, and
- mention any follow-up work or known limits.

## Validation Checklist

Before opening a PR, confirm that:

- Maven tests pass when your change affects application logic,
- setup commands in docs match the real project structure,
- API paths in docs reflect the current controllers and config, and
- deployment notes distinguish between app-level and proxy-level endpoints.
