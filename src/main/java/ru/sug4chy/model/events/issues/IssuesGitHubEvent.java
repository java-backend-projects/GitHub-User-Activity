package ru.sug4chy.model.events.issues;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class IssuesGitHubEvent extends GitHubEvent<IssuesGitHubPayload> {

    public IssuesGitHubEvent() {
        this.type = GitHubEventType.ISSUES_EVENT;
    }
}