package ru.sug4chy.model.abstractions;

import lombok.Getter;
import lombok.Setter;
import ru.sug4chy.model.GitHubRepo;
import ru.sug4chy.model.enums.GitHubEventType;

@Getter
@Setter
public abstract class GitHubEvent<TPayload extends GitHubPayload> {

    protected GitHubEventType type;
    protected GitHubRepo repo;
    protected TPayload payload;
}