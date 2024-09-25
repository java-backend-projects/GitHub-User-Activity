package ru.sug4chy.apiclient

import ru.sug4chy.model.GitHubEvent

interface GitHubApiClient {

    suspend fun listEventsForAuthenticatedUser(
        username: String
    ): Array<GitHubEvent<*>>
}