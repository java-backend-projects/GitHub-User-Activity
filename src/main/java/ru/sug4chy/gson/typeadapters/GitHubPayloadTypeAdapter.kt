package ru.sug4chy.gson.typeadapters

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.sug4chy.model.GitHubPayload
import ru.sug4chy.model.GitHubPayload.*
import ru.sug4chy.model.enums.GitHubEventType

class GitHubPayloadTypeAdapter : TypeAdapter<GitHubPayload>() {

    lateinit var targetEventType: GitHubEventType

    @Throws(UnsupportedOperationException::class)
    override fun write(out: JsonWriter, value: GitHubPayload): Nothing =
        throw UnsupportedOperationException("GitHubPayloadTypeAdapter is intended only for deserialization.")

    override fun read(`in`: JsonReader): GitHubPayload =
        when (targetEventType) {
            GitHubEventType.COMMIT_COMMENT_EVENT -> Gson().fromJson(`in`, CommitCommentGitHubPayload::class.java)
            GitHubEventType.CREATE_EVENT -> Gson().fromJson(`in`, CreateGitHubPayload::class.java)
            GitHubEventType.DELETE_EVENT -> Gson().fromJson(`in`, DeleteGitHubPayload::class.java)
            GitHubEventType.FORK_EVENT -> Gson().fromJson(`in`, ForkGitHubPayload::class.java)
            GitHubEventType.GOLLUM_EVENT -> Gson().fromJson(`in`, GollumGitHubPayload::class.java)
            GitHubEventType.ISSUE_COMMENT_EVENT -> Gson().fromJson(`in`, IssueCommentGitHubPayload::class.java)
            GitHubEventType.ISSUES_EVENT -> Gson().fromJson(`in`, IssuesGitHubPayload::class.java)
            GitHubEventType.MEMBER_EVENT -> Gson().fromJson(`in`, MemberGitHubPayload::class.java)
            GitHubEventType.PUBLIC_EVENT -> Gson().fromJson(`in`, PublicGitHubPayload::class.java)
            GitHubEventType.PULL_REQUEST_EVENT -> Gson().fromJson(`in`, PullRequestGitHubPayload::class.java)
            GitHubEventType.PULL_REQUEST_REVIEW_EVENT -> Gson().fromJson(
                `in`,
                PullRequestReviewGitHubPayload::class.java
            )
            GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT -> Gson().fromJson(
                `in`,
                PullRequestReviewCommentGitHubPayload::class.java
            )
            GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT -> Gson().fromJson(
                `in`,
                PullRequestReviewThreadGitHubPayload::class.java
            )
            GitHubEventType.PUSH_EVENT -> Gson().fromJson(`in`, PushGitHubPayload::class.java)
            GitHubEventType.RELEASE_EVENT -> Gson().fromJson(`in`, ReleaseGitHubPayload::class.java)
            GitHubEventType.SPONSORSHIP_EVENT -> Gson().fromJson(`in`, SponsorshipGitHubPayload::class.java)
            GitHubEventType.WATCH_EVENT -> Gson().fromJson(`in`, WatchGitHubPayload::class.java)
        }
}