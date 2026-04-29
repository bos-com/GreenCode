# Open Source Evaluation — GreenCode

**Repository:** `bos-com/GreenCode`
**Snapshot date:** 29 April 2026
**Author:** Coursework submission

This file is one of four sibling reports — each in its own repository — that together evaluate the four flagship Bugema Open Source Community (BOSC) projects against open source principles. The companion files live in `bos-com/OpenCare-Core`, `bos-com/LifeLine-ICT`, and `bos-com/BOS`. Every claim below is grounded in publicly verifiable evidence from this repository, the BOSC organisation profile, and the `bos-com/bosc-governance` repository, captured on the snapshot date above.

## 1. Evaluation framework

Six principles are applied (drawn from the OSI Open Source Definition, GitHub's Open Source Guides, and CHAOSS community-health metrics):

1. **Recognised licence at root.**
2. **README covers purpose, scope, and setup.**
3. **Explicit project-level governance** (CONTRIBUTING, CODE_OF_CONDUCT, GOVERNANCE).
4. **Inclusive contribution workflow** (issue/PR templates, labels, "good first issue").
5. **Active maintenance and review velocity.**
6. **Code of conduct + security policy at repo level.**

Each principle is graded **Met / Partial / Not met / Unknown**.

## 2. Project profile

GreenCode is described in the GitHub API as the "API for GreenCode" and presented in the README as a Java/Spring Boot 3.2 backend on Java 17, supporting "an innovative platform focused on sustainable development and environmental initiatives." The codebase is a typical layered Spring scaffold (controller / service / repository / DTO / entity) with JWT authentication, Swagger/OpenAPI documentation, H2 for development, PostgreSQL for production, and Docker / Docker Compose for deployment. A `scripts/` folder ships setup, deployment, and backup helpers. The repository was created on 20 August 2025 and was last pushed on 21 April 2026, so it is **actively developed**.

The GitHub-side metrics tell a contributor-driven story: **39 forks, 0 stars, 68 open issues, 29 open and 16 closed pull requests**. A forks-to-stars ratio that high almost always indicates a coursework or cohort context, where contributors fork specifically to submit work rather than because they intend to deploy the software themselves. The repository has a homepage at `https://green-code-rose.vercel.app`.

## 3. Licensing

GreenCode ships an `MIT License` at the repository root, copyrighted to "GreenCode Project, 2024." MIT is OSI-approved and is consistent with the licensing of the rest of the BOSC organisation. The README's "License" section explicitly references this file. **Principle 1: Met.**

## 4. Governance and community files

Inspecting the root directory directly via the GitHub API shows the following files: `.gitignore`, `Dockerfile`, `LICENSE`, `README.md`, `docker-compose.yml`, `env.example`, `pom.xml`, plus the `config/`, `docs/`, `scripts/`, and `src/` directories. Notably absent are `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, and `SECURITY.md`. The README does include a short "Contributing" section describing a fork-and-PR workflow, but a contributor guide, code of conduct, and security policy are not committed to `main`.

This matters because BOSC has unusually mature **community-level** governance — a `CONSTITUTION.md`, `GOVERNANCE.md`, `CODE_OF_CONDUCT.md`, and `CONTRIBUTING.md` all live in `bos-com/bosc-governance` — but none of those documents are linked from the GreenCode README. A contributor arriving from a search engine cannot see that the community has documented a Benevolent-Dictator-for-Life decision model, a Steering Committee, and a tiered approval process.

**Encouragingly, GreenCode's own backlog acknowledges these gaps.** Open issues #97 ("Add CONTRIBUTING.md for contributor workflow"), #98 ("Documentation: Add Windows setup instructions to README.md"), and #99 ("Governance: Add Code of Conduct") explicitly target the missing artefacts, and recent pull requests (#93 "Add CONTRIBUTING.md, issue templates, and PR template", #94 "Improve README with onboarding and setup clarity", #95 "docs: improve onboarding and contribution guide") propose the fixes. As of the snapshot date those PRs were still open.

**Principle 3: Not met at the repository level. Principle 6: Not met.**

## 5. Contribution workflow

The PR list shows **45 total pull requests (29 open, 16 closed)**, with steady weekly activity from multiple contributors. Recent PR titles show that the project is in a documentation-and-onboarding phase — the bulk of recent submissions are README improvements, contributor-guide drafts, and issue-template scaffolding. That is a healthy phase for a project at this maturity.

What is missing on the workflow side is **triage hygiene**. The public issue view shows no labels, no milestones, no "good first issue" tags, and no visible assignees. For a project that has clearly attracted dozens of student contributors, that absence imposes a real cost: newcomers cannot self-serve to find a starter task, and reviewers cannot easily prioritise. Adding a small label set (`bug`, `documentation`, `good first issue`, `governance`, `enhancement`) and a milestone for the next planned release would meaningfully shift the experience.

**Principle 2: Met.** The README is detailed: it lists the technology stack, prerequisites, a quick-start, project structure, environment variables, API endpoints, security features, the development workflow, and a deployment recipe. **Principle 4: Partial** — the channel is open and active, but the triage layer is missing. **Principle 5: Met** — last push was eight days before the snapshot, and there is sustained PR activity through April 2026.

## 6. Verdict and recommendations

| Principle | Verdict |
|---|---|
| 1. Recognised licence at root | **Met** (MIT) |
| 2. README covers purpose, scope, setup | **Met** |
| 3. Explicit project-level governance | **Not met** at repo level |
| 4. Inclusive contribution workflow | **Partial** |
| 5. Active maintenance and review velocity | **Met** |
| 6. Code of conduct + security policy | **Not met** |

**Three concrete next actions, in priority order:**

1. **Merge the open governance PRs** (#93, #94, #95) and resolve the related issues (#97, #98, #99). This is essentially free progress already prepared by contributors.
2. **Link the BOSC-wide governance documents from the README** (a two-line "Governance" section pointing to `bos-com/bosc-governance` is sufficient). Alternatively, populate `bos-com/.github` with shared community-health files so they auto-apply to every project.
3. **Introduce a minimal label set and triage rhythm.** Even a once-a-week pass to label, assign, and close stale issues would noticeably improve contributor experience.

GreenCode is the second-most active of the four flagship BOSC projects (after OpenCare-Core) and the only one whose own backlog is visibly correcting its governance gaps. The recommendations above are mostly clerical rather than architectural — which is itself a sign of a project that has already done the harder work.

## 7. Sources

- GitHub REST API: `/repos/bos-com/GreenCode`, `/repos/bos-com/GreenCode/contents/`.
- GitHub web pages: `README.md`, `LICENSE`, `/issues`, `/pulls`.
- BOSC governance: `https://github.com/bos-com/bosc-governance`.
- Framework: OSI *Open Source Definition*; GitHub *Open Source Guides*; CHAOSS community-health metrics.

Counts and timestamps are accurate as of 29 April 2026 and will drift as the project develops.
