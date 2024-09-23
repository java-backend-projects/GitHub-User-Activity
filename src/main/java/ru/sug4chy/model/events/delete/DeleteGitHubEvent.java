package ru.sug4chy.model.events.delete;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class DeleteGitHubEvent extends GitHubEvent<DeleteGitHubPayload> {

    public DeleteGitHubEvent() {
        this.type = GitHubEventType.DELETE_EVENT;
    }
}