# Walkthrough — GreenCode Evaluation

This document narrates how `oss_evaluation_report.md` (in this repository) was
researched. It is one of four sibling walkthroughs; each BOSC flagship
repository carries its own evaluation file and the same six-principle rubric is
applied identically across all four.

## 1. Scoping

The brief named four repositories — GreenCode, OpenCare-Core, LifeLine-ICT, BOS
— all hosted on the `bos-com` GitHub organisation. The decision to write **one
report per repository, in each repository** (rather than a single combined
document) was taken to make each project independently auditable. This file
focuses on GreenCode; the equivalent walkthroughs for the sibling projects
appear in their respective repositories.

## 2. Data sources

Per the brief, only **verifiable evidence** was used. Every claim in the
report traces back to one of two sources:

- The GitHub REST API (`api.github.com`) for licence, default branch, fork
  count, star count, open-issue count, push timestamps, and the root file
  listing.
- The rendered HTML of `github.com/bos-com/GreenCode` for README content,
  issue list, and PR list.

Where neither source produced an answer (for example, whether the project has
"good first issue" labels visible to the public), the report records the gap
rather than guessing.

## 3. Steps taken

1. Hit `GET /repos/bos-com/GreenCode` to capture metadata: licence (MIT),
   default branch (`main`), forks (39), stars (0), open issues (68), creation
   date (20 Aug 2025), last push (21 Apr 2026), homepage
   (`green-code-rose.vercel.app`).
2. Hit `GET /repos/bos-com/GreenCode/contents/` to enumerate root files —
   confirming the presence of `LICENSE`, `README.md`, `Dockerfile`,
   `docker-compose.yml`, `pom.xml`, and the absence of `CONTRIBUTING.md`,
   `CODE_OF_CONDUCT.md`, and `SECURITY.md`.
3. Visited `/issues` to characterise scope: open issues #97, #98, #99 explicitly
   target governance gaps, which is itself a healthy signal.
4. Visited `/pulls` to confirm contribution velocity: 29 open, 16 closed (45
   total). Recent PR titles (#93, #94, #95) propose the very fixes the issues
   ask for.
5. Read `bos-com/bosc-governance` to confirm BOSC has a CONSTITUTION,
   GOVERNANCE document, and CODE_OF_CONDUCT at the community level — and to
   verify that none of those documents is currently linked from the GreenCode
   README.

## 4. Key findings (GreenCode-specific)

- **Licence: clean.** MIT at root, OSI-approved.
- **Active development.** Last push eight days before the snapshot date.
- **Governance gaps acknowledged in the backlog.** Issues #97–#99 and PRs
  #93–#95 explicitly propose the missing CONTRIBUTING.md, Code of Conduct,
  and onboarding improvements. The fixes are pending review rather than
  unrecognised.
- **Triage layer is missing.** No labels, milestones, "good first issue" tags,
  or assignees visible on the public issue or PR views. Adding these is
  cheap and high-leverage.
- **BOSC governance is invisible from the project repo.** The community-level
  governance documents in `bos-com/bosc-governance` are not linked from the
  GreenCode README, so a contributor arriving from a search engine has no
  path to the constitution or code of conduct.

## 5. Snapshot date

All counts (forks, stars, open issues, open/closed PRs) were collected on
**29 April 2026.** They will drift as the project continues. Any contradiction
between this report and the live repository should be resolved in favour of
the live repository.

## 6. Out of scope

- **Code-quality assessment.** The evaluation focuses on open-source *project*
  health (licensing, governance, workflow), not on the GreenCode codebase's
  test coverage, architectural soundness, or security posture.
- **Cultural / qualitative health.** Whether reviewers are welcoming in
  practice, whether new contributors get useful feedback, and whether merged
  contributions feel meaningful — these are not visible from the GitHub
  surface and would require interviews or contributor surveys.

A natural follow-up would pair this surface audit with both of those.
