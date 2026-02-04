# lockdown

## Automated Dependency Management

This repository includes automated dependency updates and approval:

### Dependabot Configuration
- Automatic weekly updates for Maven dependencies
- Automatic weekly updates for GitHub Actions dependencies
- PRs are automatically created with the `dependencies` label

### Auto-Approval Workflow
Dependabot PRs are automatically approved after the Java CI workflow passes successfully. This workflow:
- Triggers after the "Java CI" workflow completes
- Only approves PRs created by `dependabot[bot]`
- Requires all CI checks to pass before approval
- Does not automatically merge PRs (approval only)

To enable auto-merge of approved Dependabot PRs, you can enable auto-merge in the repository settings or use additional workflow automation.
