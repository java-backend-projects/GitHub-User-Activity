package ru.sug4chy.model.events.issues;

import ru.sug4chy.model.GitHubIssue;
import ru.sug4chy.model.abstractions.GitHubPayload;

public final class IssuesGitHubPayload extends GitHubPayload {

    // opened, edited, closed, reopened, assigned, unassigned, labeled, unlabeled
    private String action;
    private GitHubIssue issue;
}