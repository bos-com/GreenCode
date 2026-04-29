# Evaluation of Four Bugema Open Source Community Repositories Against Open Source Principles

**Author:** Coursework submission
**Date:** 29 April 2026
**Repositories under review:** GreenCode, OpenCare-Core, LifeLine-ICT, BOS
**Parent organisation:** Bugema Open Source Community (BOSC) — GitHub org `bos-com`

---

## Table of Contents

1. Introduction and Methodology
2. Open Source Principles Used as the Evaluation Framework
3. The Bugema Open Source Community in Context
4. Project 1 — GreenCode
5. Project 2 — OpenCare-Core
6. Project 3 — LifeLine-ICT
7. Project 4 — BOS
8. Cross-Project Comparison Matrix
9. Strengths, Gaps, and Recommendations
10. Conclusion
11. References and Data Sources

---

## 1. Introduction and Methodology

This report evaluates four repositories hosted by the Bugema Open Source Community (BOSC, GitHub organisation `bos-com`) against widely accepted open source principles. The four repositories were selected because BOSC pins three of them on its organisation page (GreenCode, OpenCare-Core, LifeLine-ICT) and the fourth (BOS) is the namesake project of the community itself. Together they represent the public face of BOSC as of late April 2026.

The evaluation deliberately restricts itself to **verifiable evidence** drawn from each repository's public GitHub surface: the repository metadata returned by the GitHub REST API, the file tree at the default branch, the README and LICENSE files, the issue and pull-request lists, and the BOSC governance repository (`bos-com/bosc-governance`). Where evidence is absent — for example, where a project has no LICENSE file — the absence is reported as such rather than filled in with assumptions. Quantitative figures (fork counts, issue counts, PR counts) are accurate as of the data collected on **29 April 2026** and may have shifted since.

The framework against which the projects are compared is set out in Section 2. Each project is then profiled individually (Sections 4–7) covering purpose, licence, governance, and contribution workflow, before the four are placed side by side in a comparison matrix (Section 8). Section 9 distils the cross-cutting strengths and gaps into actionable recommendations.

## 2. Open Source Principles Used as the Evaluation Framework

The Open Source Initiative's Open Source Definition is the most widely cited bar for whether software is "open source" at all, but a working open-source *project* needs more than a permissive licence. Drawing on the OSI's definition, the GitHub Open Source Guides, and the Linux Foundation's CHAOSS community-health metrics, this report uses six principles to structure the assessment of each repository:

1. **Free redistribution and a recognised licence.** A project should ship a clear, OSI-approved licence at the repository root. Without one, the default is "all rights reserved" — meaning the code is not legally open source even if it sits on a public GitHub page.
2. **Transparent source and discoverable documentation.** The README should make the project's purpose, scope, prerequisites, and entry points obvious to a first-time reader.
3. **Explicit governance.** Decision-making authority, conflict resolution, and the path from contributor to maintainer should be documented (typically in `GOVERNANCE.md`, a `CODE_OF_CONDUCT.md`, and a `CONTRIBUTING.md`).
4. **Open and inclusive contribution workflow.** Issues and pull requests should be visible, labelled, triaged, and reviewed within reasonable times. "Good first issue" tags, issue templates, and PR templates lower the barrier for new contributors.
5. **Active maintenance and review velocity.** A healthy project shows recent commits, issues being closed, and pull requests being merged rather than accumulating indefinitely.
6. **Community standards beyond code.** Code of conduct, security disclosure policy, and accessible communication channels signal that the project takes its community seriously.

Each project is graded against these six principles using the rubric **Met / Partial / Not met / Unknown**. The matrix in Section 8 summarises the results.

## 3. The Bugema Open Source Community in Context

BOSC describes itself on its GitHub organisation profile as a "collaborative innovation hub initiated by Bugema University" with the mission of "empowering inclusive communities through open knowledge, local innovation, and technology." It was founded by Muwanga Erasto Kosea with support from Open Elements and Support Care Uganda, and the contact address listed on the organisation page is `kmuwanga@bugemauniv.ac.ug`. The organisation has 67 followers as of April 2026 and hosts roughly 20 public repositories, the majority of which are student projects.

Crucially, BOSC publishes a dedicated governance repository — `bos-com/bosc-governance` — containing `CONSTITUTION.md`, `GOVERNANCE.md`, `CODE_OF_CONDUCT.md`, `CONTRIBUTING.md`, a `templates/` folder, and an MIT licence. The governance model documented there is a **Benevolent Dictator for Life (BDFL)** structure with four layers: BDFL (final authority), Steering Committee (advisory), Project Maintainers (per-project leads), and Contributors. Decisions are tiered: maintainers approve minor changes, the Steering Committee reviews major ones, and critical decisions require both a community vote and BDFL approval.

This community-level governance is important context, because it means that **BOSC has documented governance even where individual project repositories do not duplicate the documents.** A reader who lands on a single project repository cannot necessarily see the constitution or code of conduct from there, however — a discoverability issue revisited in Section 9.

## 4. Project 1 — GreenCode

**Repository:** `bos-com/GreenCode`
**Description (from API):** "API for GreenCode."
**Primary language:** Java (Spring Boot 3.2 / Java 17 per README).
**Created:** 20 August 2025. **Last push:** 21 April 2026.
**Forks:** 39. **Stars:** 0. **Open issues:** 68. **Default branch:** `main`. **Homepage:** `https://green-code-rose.vercel.app`.

### 4.1 Purpose

The README presents GreenCode as a Java/Spring Boot backend for "an innovative platform focused on sustainable development and environmental initiatives," providing REST APIs for environmental data, user authentication, and "sustainable development metrics." The codebase ships a typical layered Spring Boot scaffold (controllers, services, repositories, DTOs, entities) with JWT auth, Swagger/OpenAPI documentation, H2 for development, and PostgreSQL for production. Docker and Docker Compose files are included, alongside a `scripts/` folder with setup, deploy, and backup helpers.

### 4.2 Licensing

The repository ships an MIT Licence at the root (`LICENSE`), copyrighted to "GreenCode Project, 2024." MIT is OSI-approved, so on the *licence* dimension GreenCode meets the bar. The README also explicitly references the licence in its "License" section.

### 4.3 Governance and community files

This is where GreenCode is weakest. As of the data collection date, the repository root does **not** contain `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, or `SECURITY.md`. The README has a short "Contributing" section that describes a fork-and-PR workflow, but there is no separate contributor guide, no PR template, and no issue templates committed to `main`. The community-level documents in `bos-com/bosc-governance` are not linked from the GreenCode README either, so a contributor arriving from a Google search has no obvious path to the constitution or code of conduct.

That said, the issues list shows that **the project is aware of these gaps and is actively addressing them.** Open issues #97 ("Add CONTRIBUTING.md for contributor workflow"), #98 ("Documentation: Add Windows setup instructions to README.md"), and #99 ("Governance: Add Code of Conduct") explicitly target the missing governance artefacts, and recent pull requests (#93 "Add CONTRIBUTING.md, issue templates, and PR template", #94 "Improve README with onboarding and setup clarity", #95 "docs: improve onboarding and contribution guide") propose the fixes. As of the snapshot, those PRs were still open rather than merged.

### 4.4 Contribution workflow

GreenCode has the most active contribution flow of the four projects. The PR list shows 29 open and 16 closed pull requests (45 total), with steady activity from multiple contributors. The high number of forks (39) relative to stars (0) is a strong signal that contributors are forking specifically to submit work — typical of student cohorts coordinating around a shared project. Issues span documentation, setup, governance, and feature work; however, they appear unlabeled and unassigned in the public view, with no visible "good first issue" tags or milestones, which makes it harder for newcomers to self-serve.

### 4.5 Health summary

GreenCode is a **partially mature** open-source project. Its licence is clean, its codebase is non-trivial, and contribution activity is real. The gaps are governance documentation at the repository level and triage hygiene (labels, assignees, milestones) on issues and PRs. Crucially, the project's own backlog acknowledges these gaps — which is itself a healthy signal.

## 5. Project 2 — OpenCare-Core

**Repository:** `bos-com/OpenCare-Core`
**Description (from API):** "Empowering Healthy Through Technology in Africa and beyond."
**Primary language:** Python (Django, per README).
**Created:** 20 August 2025. **Last push:** 24 April 2026.
**Forks:** 38. **Stars:** 1. **Open issues:** 66.

### 5.1 Purpose

OpenCare-Core is described in its README as "a comprehensive health informatics platform backend built with Django, designed specifically for healthcare management in Africa." The repository is organised around Django apps covering core functionality, patient management, healthcare-worker administration, facility operations, medical records (with an explicit goal of FHIR compliance), and analytics. The presence of `manage.py`, `requirements.txt`, `requirements-dev.txt`, `Dockerfile`, and `docker-compose.yml` indicates a runnable Django scaffold rather than a placeholder.

### 5.2 Licensing

OpenCare-Core ships an MIT `LICENSE` at the repository root. As with GreenCode, the licence is OSI-approved and unambiguous.

### 5.3 Governance and community files

OpenCare-Core is the **most governance-complete** of the four core repositories: it has both `LICENSE` and `CONTRIBUTING.md` at the root. It does not have `CODE_OF_CONDUCT.md` or `SECURITY.md`, however. As with GreenCode, the BOSC-wide governance documents in `bos-com/bosc-governance` are not directly linked from the project README.

### 5.4 Contribution workflow

The PR list shows roughly 35 open and 19 closed pull requests (54 total). Activity is recent — the most recent push was 24 April 2026, and PRs continue to be opened in late April. The ratio of open to closed PRs (~1.8:1) suggests merge velocity is lagging behind submission velocity, which is a common pattern in projects where contributor capacity outpaces reviewer capacity. The forks-to-stars ratio (38:1) again suggests contributor-driven rather than user-driven engagement.

### 5.5 Health summary

OpenCare-Core is structurally the **most mature** of the four — it has a runnable Django application, an explicit contributor guide, MIT licensing, and active development. Its main risks are review-capacity bottlenecks and the lack of a code of conduct and security policy at the repository level.

## 6. Project 3 — LifeLine-ICT

**Repository:** `bos-com/LifeLine-ICT`
**Description (from API):** "ICT-Driven Disaster Preparedness & Early Warning project by the Bugema Open Source Community (BOSC). We build open-source tools to protect communities from climate risks like floods and droughts, using real-time alerts, sensor-based monitoring, GIS mapping, and community dissemination."
**Primary language:** Python (FastAPI per README).
**Created:** 20 August 2025. **Last push:** 13 November 2025.
**Forks:** 38. **Stars:** 1. **Open issues:** 75.

### 6.1 Purpose

LifeLine-ICT has the most explicitly social-impact-oriented description of the four. The README and `plan.md` describe a layered architecture combining FastAPI backends, ESP32-based IoT sensor nodes, SQLAlchemy/Alembic on SQLite, and (planned) GIS analytics and dashboards. The stated tech stack is "98.6% Python, 1.4% other" and the project explicitly targets disaster preparedness for flood and drought risks.

### 6.2 Licensing — the critical gap

**LifeLine-ICT does not ship a `LICENSE` file at the repository root.** This is the single most important finding of this evaluation. Under default copyright law, source code published without a licence is "all rights reserved": the public can view it but cannot legally copy, modify, or redistribute it. The GitHub REST API correspondingly returns no licence object for this repository. By the OSI's Open Source Definition, **LifeLine-ICT is not currently open source**, regardless of how publicly it is hosted or how prominently BOSC features it on its organisation page. The README does mention "MIT" and "Apache" in passing, but a textual mention is not a licence grant — the repository needs a committed `LICENSE` file with the actual licence text.

### 6.3 Governance and community files

The repository root contains only `README.md`, `plan.md`, a `.github/` folder, and the `backend/`, `docs/`, and `iot/` directories. There is no `CONTRIBUTING.md`, no `CODE_OF_CONDUCT.md`, and no `LICENSE`. As with the other projects, there is no inline link to the BOSC-wide governance repository.

### 6.4 Contribution workflow

The contribution data is striking: **41 open pull requests against only 2 closed.** Combined with the fact that the most recent push was 13 November 2025 — over five months before the evaluation date — this points to a project that attracted contributor interest (38 forks, 75 open issues, dozens of PRs) but where review and merge activity has effectively stopped. This is the classic signature of a project that has lost its active maintainer.

### 6.5 Health summary

LifeLine-ICT has a compelling mission and visibly received contributor effort, but it falls short on two of the most important open-source principles: it has no licence, and its review pipeline appears stalled. The mission described in the README is precisely the kind of work the BOSC community exists to do, which makes the absence of basic governance hygiene more disappointing rather than less.

## 7. Project 4 — BOS

**Repository:** `bos-com/BOS`
**Description (from API):** "BOS (Bugema Operating System) the internet OS! Free, Open Source, Self hostable."
**Primary language:** Not specified.
**Created:** 26 August 2025. **Last push:** 26 August 2025.
**Forks:** 37. **Stars:** 0. **Open issues:** 73. **Repository size:** 1 KB.

### 7.1 Purpose and current state

BOS is the namesake project of the Bugema Open Source community — described aspirationally as a "free, open source, self-hostable internet operating system." In practice, however, the repository is **a placeholder**: only `LICENSE` and `README.md` are present at the root, the README contains the single-line description above and nothing else, and no source code has been pushed since the initial commit on 26 August 2025. The repository size is 1 KB.

### 7.2 Licensing

Despite being effectively empty, BOS does ship an MIT `LICENSE` at the root. So in the narrow sense of "if there were code here, it would be openly licensed," BOS meets the licence principle.

### 7.3 Governance and community files

There is no `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`, or `GOVERNANCE.md` in BOS. There is also no documented architecture, no roadmap, and no statement of scope beyond the single-line README.

### 7.4 Contribution workflow

The contribution metrics are paradoxical and informative: BOS has 37 forks and 73 open issues against an empty codebase. This pattern strongly suggests that the issues and forks are **pedagogical** — i.e. that BOS is being used as a teaching scaffold where students fork and file issues as part of an exercise — rather than reflecting genuine product development. Whatever the reason, an outside observer cannot evaluate code quality, architecture, or roadmap, because none has been published.

### 7.5 Health summary

BOS is best described as a **stub**. It carries the brand of the BOSC community but does not currently contain a project in the working sense. From an open-source-principles perspective there is little to evaluate beyond the licence; the more useful question is whether BOS should either be archived or be filled in with the architecture and scope statement its name suggests it deserves.

## 8. Cross-Project Comparison Matrix

The table below applies the six-principle framework from Section 2. **Met** = principle clearly satisfied; **Partial** = some evidence but with material gaps; **Not met** = principle clearly violated; **Unknown** = insufficient public evidence.

| Principle | GreenCode | OpenCare-Core | LifeLine-ICT | BOS |
|---|---|---|---|---|
| **1. Recognised licence at root** | Met (MIT) | Met (MIT) | **Not met** (no LICENSE file) | Met (MIT) |
| **2. README covers purpose, scope, setup** | Met | Met | Met | **Not met** (one-line README) |
| **3. Explicit project-level governance** | **Not met** at repo level (BOSC-wide governance exists but is not linked) | **Not met** at repo level | **Not met** | **Not met** |
| **4. Inclusive contribution workflow** | Partial (active PRs, no issue/PR templates merged, no labels) | Partial (CONTRIBUTING.md present, no labels) | **Not met** (PRs accumulating, almost none merged) | **Not met** (no codebase) |
| **5. Active maintenance and review velocity** | Met (recent activity April 2026) | Met (recent activity April 2026) | **Not met** (no push since Nov 2025; 41 open vs 2 closed PRs) | **Not met** (no push since Aug 2025) |
| **6. Code of conduct + security policy at repo level** | **Not met** | **Not met** | **Not met** | **Not met** |

A few observations from the matrix:

- **No project replicates the BOSC governance documents at the repository level.** This is a discoverability problem — the documents exist, but a contributor reading a single project repository cannot see them.
- **OpenCare-Core leads on governance hygiene** because it is the only repository with a committed `CONTRIBUTING.md`.
- **LifeLine-ICT's missing licence is the single most serious individual finding**, because it converts a public repository into something that is technically not redistributable.
- **GreenCode and OpenCare-Core are the two genuinely active projects** of the four, and they are also the two best-positioned to act on the recommendations in Section 9.

## 9. Strengths, Gaps, and Recommendations

### 9.1 Cross-cutting strengths

1. **Real social mission.** All four projects target genuine sub-Saharan African challenges — environmental sustainability, healthcare informatics, disaster preparedness, and digital infrastructure. This gives BOSC a coherent identity that many student-led communities lack.
2. **Genuine contributor engagement.** Three of the four repositories have 37–39 forks and dozens of open pull requests. Whatever the eventual merge rate, there is real student participation behind the numbers.
3. **A documented community governance model.** The `bos-com/bosc-governance` repository is unusually well structured for a student-led community: it has a constitution, a governance document, a code of conduct, contribution guidelines, and templates. Many open-source projects with far more visibility lack equivalent artefacts.
4. **Permissive licensing where licensing exists.** MIT is the dominant choice across the org, which keeps the legal surface area simple and consistent.

### 9.2 Cross-cutting gaps

1. **Project repositories don't inherit or link to the BOSC governance documents.** New contributors landing on, say, the GreenCode repository have no visible path to the constitution or code of conduct. The `.github` repository's community-health files can largely fix this with no per-project work.
2. **Issue and PR triage is thin.** None of the four projects shows visible labels, milestones, "good first issue" tags, or assignees. The cost of adding these is low and the payoff for newcomers is high.
3. **Review velocity is uneven.** OpenCare-Core's open:closed PR ratio (~1.8:1) is workable but slipping; LifeLine-ICT's (~20:1) indicates a stalled review pipeline; BOS has no codebase to review.
4. **Security disclosure is undocumented.** None of the four repositories has a `SECURITY.md`, and OpenCare-Core in particular — as a healthcare informatics project handling potentially sensitive records — should have a documented vulnerability-disclosure pathway.
5. **LifeLine-ICT is not currently open source in the OSI sense.** This is the most urgent fix in the entire portfolio.

### 9.3 Concrete recommendations

In rough priority order:

1. **Add a `LICENSE` file to LifeLine-ICT.** The README mentions both MIT and Apache; the maintainers should pick one (consistency with the rest of the org points to MIT) and commit the licence text.
2. **Centralise community-health files in `bos-com/.github`.** GitHub auto-applies a `CODE_OF_CONDUCT.md`, `CONTRIBUTING.md`, `SECURITY.md`, and issue/PR templates from a `.github` repository in the same org. BOSC already has a `.github` repository, so adding these files there propagates them to every project for free.
3. **Link the BOSC governance documents from each project README.** A two-line "Governance" section pointing to `bos-com/bosc-governance` is enough.
4. **Triage existing issues and PRs in GreenCode and OpenCare-Core.** Add labels (`bug`, `documentation`, `good first issue`, `governance`), assign maintainers, and close stale items. This addresses the review-velocity gap without requiring new code.
5. **Resolve the LifeLine-ICT review stall.** Either appoint a maintainer with merge rights, or — if the project is paused — say so explicitly in the README so contributors don't waste effort on PRs that will not be reviewed.
6. **Decide BOS's scope or archive it.** Either commit a real architecture and roadmap or mark the repository as archived. An empty repository with the community's brand attached is a credibility risk.
7. **Add a `SECURITY.md`** — at the very least to OpenCare-Core, where the data-sensitivity argument is strongest.

## 10. Conclusion

The four BOSC repositories evaluated here form a recognisable spectrum. **OpenCare-Core** is the closest to a healthy open-source project: licensed, documented, actively maintained, with a contributor guide. **GreenCode** is close behind, with active contribution and a backlog that explicitly acknowledges and is fixing its own governance gaps. **LifeLine-ICT** has a strong mission and visible contributor interest but is held back by the absence of a licence and a stalled review pipeline. **BOS** is a placeholder more than a project.

Across all four, the most striking single observation is that BOSC has done unusually thorough community-level governance work in `bos-com/bosc-governance` — and almost none of that work is visible from the project repositories themselves. Closing that discoverability gap is high-leverage: it would lift every project from "not met" to at least "partial" on the governance principle without requiring any new prose to be written. Combined with the other recommendations in Section 9 — most urgently, adding a licence file to LifeLine-ICT — BOSC could move three of these four projects to a substantially healthier open-source posture within a single sprint of administrative work.

What this study cannot tell us — and would require interviews or longitudinal tracking to assess — is the *cultural* health of the community: how welcoming reviewers are in practice, whether new contributors get useful feedback, and whether merged contributions feel meaningful to the people who made them. The signals visible from the GitHub surface are encouraging on engagement (high fork counts, active issues, recent commits in the leading two repositories) but mute on quality of experience. A natural follow-up study would pair this surface-level audit with a contributor survey.

## 11. References and Data Sources

All quantitative data in this report was collected on **29 April 2026** from the following sources:

- GitHub REST API endpoints: `/orgs/bos-com`, `/orgs/bos-com/repos`, `/repos/bos-com/{GreenCode|OpenCare-Core|LifeLine-ICT|BOS}`, and `/repos/bos-com/{repo}/contents/`.
- The HTML pages for each repository's `README.md`, `LICENSE`, issues list, and pull-requests list at `https://github.com/bos-com/{repo}`.
- The BOSC governance repository: `https://github.com/bos-com/bosc-governance`.
- The BOSC organisation profile: `https://github.com/bos-com`.

Framework references:

- Open Source Initiative, *The Open Source Definition* (osi-licenses authoritative version), used for principle 1.
- GitHub, *Open Source Guides* (`opensource.guide`), used for principles 2–6.
- Linux Foundation, *CHAOSS Community Health Analytics* metrics, used to inform principle 5 (review velocity, issue response time).

Repository fork, star, issue, and PR counts cited in this report are point-in-time snapshots and will drift as the projects continue to develop. Any contradiction between this report and the live repositories should be resolved in favour of the live repositories.
