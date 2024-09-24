package ru.sug4chy.gson.typeadapters

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubEvent.*
import ru.sug4chy.model.GitHubPayload
import ru.sug4chy.model.GitHubPayload.*
import ru.sug4chy.model.GitHubRepo
import ru.sug4chy.model.enums.GitHubEventType
import java.io.IOException

class GitHubEventTypeAdapter(
    private val payloadAdapter: GitHubPayloadTypeAdapter = GitHubPayloadTypeAdapter()
) : TypeAdapter<GitHubEvent<*>>() {

    @Throws(UnsupportedOperationException::class)
    override fun write(out: JsonWriter, value: GitHubEvent<*>): Nothing =
        throw UnsupportedOperationException("GitHubEventTypeAdapter is intended only for deserialization.")

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): GitHubEvent<*> {
        `in`.beginObject()

        lateinit var type: GitHubEventType
        lateinit var repo: GitHubRepo
        lateinit var payload: GitHubPayload

        while (`in`.hasNext()) {
            when (`in`.nextName()) {
                "type" -> type = GitHubEventType.fromPascalCaseString(`in`.nextString())
                "repo" -> repo = Gson().fromJson(`in`, GitHubRepo::class.java)
                "payload" -> payloadAdapter.apply {
                    targetEventType = type
                    payload = read(`in`)
                }
                else -> `in`.skipValue()
            }
        }

        `in`.endObject()

        return when (type) {
            GitHubEventType.COMMIT_COMMENT_EVENT -> CommitCommentGitHubEvent(
                repo,
                payload as CommitCommentGitHubPayload
            )
            GitHubEventType.CREATE_EVENT -> CreateGitHubEvent(repo, payload as CreateGitHubPayload)
            GitHubEventType.DELETE_EVENT -> DeleteGitHubEvent(repo, payload as DeleteGitHubPayload)
            GitHubEventType.FORK_EVENT -> ForkGitHubEvent(repo, payload as ForkGitHubPayload)
            GitHubEventType.GOLLUM_EVENT -> GollumGitHubEvent(repo, payload as GollumGitHubPayload)
            GitHubEventType.ISSUE_COMMENT_EVENT -> IssueCommentGitHubEvent(repo, payload as IssueCommentGitHubPayload)
            GitHubEventType.ISSUES_EVENT -> IssuesGitHubEvent(repo, payload as IssuesGitHubPayload)
            GitHubEventType.MEMBER_EVENT -> MemberGitHubEvent(repo, payload as MemberGitHubPayload)
            GitHubEventType.PUBLIC_EVENT -> PublicGitHubEvent(repo, payload as PublicGitHubPayload)
            GitHubEventType.PULL_REQUEST_EVENT -> PullRequestGitHubEvent(repo, payload as PullRequestGitHubPayload)
            GitHubEventType.PULL_REQUEST_REVIEW_EVENT -> PullRequestReviewGitHubEvent(
                repo,
                payload as PullRequestReviewGitHubPayload
            )
            GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT -> PullRequestReviewCommentGitHubEvent(
                repo,
                payload as PullRequestReviewCommentGitHubPayload
            )
            GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT -> PullRequestReviewThreadGitHubEvent(
                repo,
                payload as PullRequestReviewThreadGitHubPayload
            )
            GitHubEventType.PUSH_EVENT -> PushGitHubEvent(repo, payload as PushGitHubPayload)
            GitHubEventType.RELEASE_EVENT -> ReleaseGitHubEvent(repo, payload as ReleaseGitHubPayload)
            GitHubEventType.SPONSORSHIP_EVENT -> SponsorshipGitHubEvent(repo, payload as SponsorshipGitHubPayload)
            GitHubEventType.WATCH_EVENT -> WatchGitHubEvent(repo, payload as WatchGitHubPayload)
        }
    }
}