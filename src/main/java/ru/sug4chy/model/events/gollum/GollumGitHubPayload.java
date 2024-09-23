package ru.sug4chy.model.events.gollum;

import ru.sug4chy.model.abstractions.GitHubPayload;
import ru.sug4chy.model.events.gollum.models.GitHubWikiPage;

public final class GollumGitHubPayload extends GitHubPayload {

    private GitHubWikiPage[] pages;
}