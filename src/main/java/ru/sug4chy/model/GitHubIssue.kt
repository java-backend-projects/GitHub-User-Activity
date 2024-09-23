package ru.sug4chy.model

import kotlinx.serialization.SerialName

class GitHubIssue(
    @SerialName("pull_request")
    private val pullRequest: Any?
) {
    val isIssue: Boolean
        get() = pullRequest == null

    fun isPullRequest(): Boolean {
        return pullRequest != null
    }
}