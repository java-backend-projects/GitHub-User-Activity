package ru.sug4chy.model

import com.google.gson.annotations.SerializedName

class GitHubIssue(
    @SerializedName("pull_request")
    private val pullRequest: Any?
) {
    val isIssue: Boolean
        get() = pullRequest == null

    fun isPullRequest(): Boolean {
        return pullRequest != null
    }
}