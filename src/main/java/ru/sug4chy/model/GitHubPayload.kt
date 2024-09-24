package ru.sug4chy.model

import com.google.gson.annotations.SerializedName

sealed class GitHubPayload {
    data class CommitCommentGitHubPayload(val action: String = "created") : GitHubPayload()

    data class CreateGitHubPayload(
        @SerializedName("ref_type")
        val refType: String,
    ) : GitHubPayload()

    data class DeleteGitHubPayload(
        @SerializedName("ref_type")
        val refType: String,
    ) : GitHubPayload()

    data object ForkGitHubPayload : GitHubPayload()

    class GollumGitHubPayload(
        val pages: Array<GitHubWikiPage>
    ) : GitHubPayload()

    data class IssueCommentGitHubPayload(
        val action: String,
        val issue: GitHubIssue,
    ) : GitHubPayload()

    data class IssuesGitHubPayload(
        val action: String,
        val issue: GitHubIssue,
    ) : GitHubPayload()

    data class MemberGitHubPayload(
        val action: String
    ) : GitHubPayload()

    data object PublicGitHubPayload : GitHubPayload()

    data class PullRequestGitHubPayload(
        val action: String
    ) : GitHubPayload()

    data class PullRequestReviewGitHubPayload(
        val action: String ="created"
    ) : GitHubPayload()

    data class PullRequestReviewCommentGitHubPayload(
        val action: String
    ) : GitHubPayload()

    data class PullRequestReviewThreadGitHubPayload(
        val action: String
    ) : GitHubPayload()

    class PushGitHubPayload(
        val commits: Array<Any>
    ) : GitHubPayload()

    data class ReleaseGitHubPayload(
        val action: String
    ) : GitHubPayload()

    data class SponsorshipGitHubPayload(
        val action: String
    ) : GitHubPayload()

    data class WatchGitHubPayload(
        val action: String
    ) : GitHubPayload()
}