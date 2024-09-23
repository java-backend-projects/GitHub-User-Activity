package ru.sug4chy.model.events.issuecomment;

import ru.sug4chy.model.abstractions.GitHubPayload;
import ru.sug4chy.model.GitHubIssue;

public final class IssueCommentGitHubPayload extends GitHubPayload {

    // created, edited, deleted
    private String action;
    private GitHubIssue issue;
}