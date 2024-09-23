package ru.sug4chy.model.events.issuecomment;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class IssueCommentGitHubEvent extends GitHubEvent<IssueCommentGitHubPayload> {

    public IssueCommentGitHubEvent() {
        this.type = GitHubEventType.ISSUE_COMMENT_EVENT;
    }
}