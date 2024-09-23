package ru.sug4chy.model.events.commitcomment;

import ru.sug4chy.model.abstractions.GitHubPayload;

public final class CommitCommentGitHubPayload extends GitHubPayload {
    private String action = "created";
}