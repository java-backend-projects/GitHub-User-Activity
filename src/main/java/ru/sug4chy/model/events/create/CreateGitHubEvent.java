package ru.sug4chy.model.events.create;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class CreateGitHubEvent extends GitHubEvent<CreateGitHubPayload> {

    public CreateGitHubEvent() {
        this.type = GitHubEventType.CREATE_EVENT;
    }
}