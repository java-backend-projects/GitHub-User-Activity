package ru.sug4chy.model.events.gollum;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class GollumGitHubEvent extends GitHubEvent<GollumGitHubPayload> {

    public GollumGitHubEvent() {
        this.type = GitHubEventType.GOLLUM_EVENT;
    }
}