package ru.sug4chy.gson.typeadapters

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.sug4chy.model.GitHubActor
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubEvent.*
import ru.sug4chy.model.GitHubPayload
import ru.sug4chy.model.GitHubPayload.*
import ru.sug4chy.model.GitHubRepo
import ru.sug4chy.model.enums.GitHubEventType
import java.io.IOException
import java.time.LocalDateTime

class GitHubEventTypeAdapter(
    private val payloadAdapter: GitHubPayloadTypeAdapter = GitHubPayloadTypeAdapter(),
    private val localDateTimeTypeAdapter: LocalDateTimeTypeAdapter = LocalDateTimeTypeAdapter()
) : TypeAdapter<GitHubEvent<*>>() {

    @Throws(UnsupportedOperationException::class)
    override fun write(out: JsonWriter, value: GitHubEvent<*>): Nothing =
        throw UnsupportedOperationException("GitHubEventTypeAdapter is intended only for deserialization.")

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): GitHubEvent<*> {
        `in`.beginObject()

        lateinit var type: GitHubEventType
        lateinit var actor: GitHubActor
        lateinit var repo: GitHubRepo
        lateinit var payload: GitHubPayload
        lateinit var createdAt: LocalDateTime

        while (`in`.hasNext()) {
            when (`in`.nextName()) {
                "type" -> type = GitHubEventType.fromPascalCaseString(`in`.nextString())
                "actor" -> actor = Gson().fromJson(`in`, GitHubActor::class.java)
                "repo" -> repo = Gson().fromJson(`in`, GitHubRepo::class.java)
                "payload" -> payloadAdapter.apply {
                    targetEventType = type
                    payload = read(`in`)
                }

                "created_at" -> createdAt = localDateTimeTypeAdapter.read(`in`)
                else -> `in`.skipValue()
            }
        }

        `in`.endObject()

        return when (type) {
            GitHubEventType.COMMIT_COMMENT_EVENT -> CommitCommentGitHubEvent(
                actor,
                repo,
                payload as CommitCommentGitHubPayload,
                createdAt
            )
            GitHubEventType.CREATE_EVENT -> CreateGitHubEvent(actor, repo, payload as CreateGitHubPayload, createdAt)
            GitHubEventType.DELETE_EVENT -> DeleteGitHubEvent(actor, repo, payload as DeleteGitHubPayload, createdAt)
            GitHubEventType.FORK_EVENT -> ForkGitHubEvent(actor, repo, payload as ForkGitHubPayload, createdAt)
            GitHubEventType.GOLLUM_EVENT -> GollumGitHubEvent(actor, repo, payload as GollumGitHubPayload, createdAt)
            GitHubEventType.ISSUE_COMMENT_EVENT -> IssueCommentGitHubEvent(
                actor,
                repo,
                payload as IssueCommentGitHubPayload,
                createdAt
            )
            GitHubEventType.ISSUES_EVENT -> IssuesGitHubEvent(actor, repo, payload as IssuesGitHubPayload, createdAt)
            GitHubEventType.MEMBER_EVENT -> MemberGitHubEvent(actor, repo, payload as MemberGitHubPayload, createdAt)
            GitHubEventType.PUBLIC_EVENT -> PublicGitHubEvent(actor, repo, payload as PublicGitHubPayload, createdAt)
            GitHubEventType.PULL_REQUEST_EVENT -> PullRequestGitHubEvent(
                actor,
                repo,
                payload as PullRequestGitHubPayload,
                createdAt
            )
            GitHubEventType.PULL_REQUEST_REVIEW_EVENT -> PullRequestReviewGitHubEvent(
                actor,
                repo,
                payload as PullRequestReviewGitHubPayload,
                createdAt
            )
            GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT -> PullRequestReviewCommentGitHubEvent(
                actor,
                repo,
                payload as PullRequestReviewCommentGitHubPayload,
                createdAt
            )
            GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT -> PullRequestReviewThreadGitHubEvent(
                actor,
                repo,
                payload as PullRequestReviewThreadGitHubPayload,
                createdAt
            )
            GitHubEventType.PUSH_EVENT -> PushGitHubEvent(actor, repo, payload as PushGitHubPayload, createdAt)
            GitHubEventType.RELEASE_EVENT -> ReleaseGitHubEvent(actor, repo, payload as ReleaseGitHubPayload, createdAt)
            GitHubEventType.SPONSORSHIP_EVENT -> SponsorshipGitHubEvent(
                actor,
                repo,
                payload as SponsorshipGitHubPayload,
                createdAt
            )
            GitHubEventType.WATCH_EVENT -> WatchGitHubEvent(actor, repo, payload as WatchGitHubPayload, createdAt)
        }
    }
}