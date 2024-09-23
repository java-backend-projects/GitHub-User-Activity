package ru.sug4chy.model.events.member;

import ru.sug4chy.model.abstractions.GitHubEvent;
import ru.sug4chy.model.enums.GitHubEventType;

public final class MemberGitHubEvent extends GitHubEvent<MemberGitHubPayload> {

    public MemberGitHubEvent() {
        this.type = GitHubEventType.MEMBER_EVENT;
    }
}