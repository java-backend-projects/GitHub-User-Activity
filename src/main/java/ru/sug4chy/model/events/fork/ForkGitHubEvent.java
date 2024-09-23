package ru.sug4chy.model.events.fork;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class ForkGitHubEvent extends GitHubEvent<ForkGitHubPayload> {

    public ForkGitHubEvent() {
        this.type = GitHubEventType.FORK_EVENT;
    }
}