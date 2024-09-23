package ru.sug4chy.model.events.commitcomment;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class CommitCommentGitHubEvent extends GitHubEvent<CommitCommentGitHubPayload> {

    public CommitCommentGitHubEvent() {
        this.type = GitHubEventType.COMMIT_COMMENT_EVENT;
    }
}