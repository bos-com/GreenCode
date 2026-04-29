# Walkthrough — How the BOSC OSS Evaluation Was Researched

This document narrates the research process behind `oss_evaluation_report.md`,
so the work can be audited and reproduced.

## 1. Scoping

The brief named four repositories: GreenCode, OpenCare-Core, LifeLine-ICT, and
BOS. The local working directory was a clone of `bos-com/GreenCode`, which
established the GitHub organisation (`bos-com`) and confirmed the other three
projects live in the same org. Confirming the org also surfaced the
`bos-com/bosc-governance` repository, which became important context.

## 2. Data collection — the rule of "verifiable only"

The user's instruction was to use **only verified facts**, flagging unknowns
rather than inventing plausible content. Every quantitative or factual claim in
the report therefore traces back to one of two sources:

- The GitHub REST API (`api.github.com`) — for license, default branch, fork
  count, star count, open-issue count, creation/push timestamps, and root file
  listings.
- The rendered HTML of each repository on `github.com` — for README content,
  pull-request and issue lists, and badges.

Where neither source produced an answer (for example, whether a project has
"good first issue" labels visible to the public), the report says so rather
than guessing.

## 3. Per-project research steps

For each of GreenCode, OpenCare-Core, LifeLine-ICT, and BOS:

1. Hit `GET /repos/bos-com/{repo}` to capture metadata.
2. Hit `GET /repos/bos-com/{repo}/contents/` to enumerate root files — this is
   how the absence of a LICENSE file in LifeLine-ICT was confirmed (rather than
   relying on the README, which mentioned licences in passing).
3. Visit the rendered repo page to read the README and observe top-level
   structure.
4. Visit `/pulls` and `/issues` to characterise contribution activity (open vs
   closed counts, recency, sample titles).

The BOSC organisation profile and the `bosc-governance` repository were also
read in full, because they materially affect the governance assessment of the
four projects.

## 4. The framework

Six principles were chosen as the evaluation rubric:

1. Recognised licence at root.
2. README that covers purpose, scope, and setup.
3. Explicit project-level governance.
4. Inclusive contribution workflow (issue/PR templates, labels, "good first
   issue").
5. Active maintenance and review velocity.
6. Code of conduct + security policy at repo level.

These were drawn from the Open Source Initiative's Open Source Definition,
GitHub's Open Source Guides, and Linux Foundation CHAOSS metrics. The rubric
was applied identically to every project.

## 5. Key findings as they emerged

- **LifeLine-ICT has no LICENSE file.** Discovered by listing the repository
  root via the API; cross-checked against the GitHub UI. This was the single
  most material finding in the evaluation: absent a licence, public source
  code is "all rights reserved" by default and is not open source in the OSI
  sense, despite being publicly visible.
- **BOS is effectively empty.** Repository size of 1 KB, two files at the
  root, and a single-line README. The 37 forks and 73 open issues against an
  empty codebase suggest a pedagogical / scaffolding use, not a real project.
- **OpenCare-Core is the most governance-complete** of the four core
  repositories — it is the only one with a committed `CONTRIBUTING.md`.
- **GreenCode's own backlog acknowledges its governance gaps.** Issues #97,
  #98, and #99 explicitly call for a CONTRIBUTING.md, Windows setup notes,
  and a Code of Conduct — a healthier signal than silently lacking them.
- **BOSC has unusually mature community-level governance** in
  `bos-com/bosc-governance` (constitution, governance, code of conduct,
  contribution guide, templates), but **none of it is linked from the project
  repositories**, which is a discoverability problem rather than a content
  problem.

## 6. Snapshot date

All counts (forks, stars, open issues, open/closed PRs) were collected on
**29 April 2026**. They will drift as the projects continue to develop. The
report calls this out explicitly so future readers can re-check.

## 7. What this walkthrough did not cover

- **Cultural / qualitative health** of the contributor community — whether
  reviewers are welcoming, whether new contributors get useful feedback, and
  whether merged work feels meaningful. None of this is visible from the
  GitHub surface alone; it would require interviews or contributor surveys.
- **Code-level quality assessment.** The report focused on open-source
  *project* health (licensing, governance, workflow), not code quality, test
  coverage, or architectural soundness.

A follow-up study could pair this surface audit with both of those.
