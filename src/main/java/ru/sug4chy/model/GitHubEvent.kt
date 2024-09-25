package ru.sug4chy.model

import com.google.gson.annotations.SerializedName
import ru.sug4chy.formatter.GitHubEventFormatter
import ru.sug4chy.model.GitHubPayload.*
import ru.sug4chy.model.enums.GitHubEventType
import java.time.LocalDateTime

sealed class GitHubEvent<out TPayload : GitHubPayload>(
    val type: GitHubEventType,
    private val actor: GitHubActor,
    private val repo: GitHubRepo,
    val payload: TPayload,

    @SerializedName("created_at")
    val createdAt: LocalDateTime
) {

    val repoFullName: String
        get() = "${actor.displayLogin}/${repo.name}"

    class CommitCommentGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: CommitCommentGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<CommitCommentGitHubPayload>(GitHubEventType.COMMIT_COMMENT_EVENT, actor, repo, payload, createdAt)

    class CreateGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: CreateGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<CreateGitHubPayload>(GitHubEventType.CREATE_EVENT, actor, repo, payload, createdAt)

    class DeleteGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: DeleteGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<DeleteGitHubPayload>(GitHubEventType.DELETE_EVENT, actor, repo, payload, createdAt)

    class ForkGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: ForkGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<ForkGitHubPayload>(GitHubEventType.FORK_EVENT, actor, repo, payload, createdAt)

    class GollumGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: GollumGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<GollumGitHubPayload>(GitHubEventType.GOLLUM_EVENT, actor, repo, payload, createdAt)

    class IssueCommentGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: IssueCommentGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<IssueCommentGitHubPayload>(GitHubEventType.ISSUE_COMMENT_EVENT, actor, repo, payload, createdAt)

    class IssuesGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: IssuesGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<IssuesGitHubPayload>(GitHubEventType.ISSUES_EVENT, actor, repo, payload, createdAt)

    class MemberGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: MemberGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<MemberGitHubPayload>(GitHubEventType.MEMBER_EVENT, actor, repo, payload, createdAt)

    class PublicGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PublicGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PublicGitHubPayload>(GitHubEventType.PUBLIC_EVENT, actor, repo, payload, createdAt)

    class PullRequestGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PullRequestGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PullRequestGitHubPayload>(GitHubEventType.PULL_REQUEST_EVENT, actor, repo, payload, createdAt)

    class PullRequestReviewGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PullRequestReviewGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PullRequestReviewGitHubPayload>(
        GitHubEventType.PULL_REQUEST_REVIEW_EVENT,
        actor,
        repo,
        payload,
        createdAt
    )

    class PullRequestReviewCommentGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PullRequestReviewCommentGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PullRequestReviewCommentGitHubPayload>(
        GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT,
        actor,
        repo,
        payload,
        createdAt
    )

    class PullRequestReviewThreadGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PullRequestReviewThreadGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PullRequestReviewThreadGitHubPayload>(
        GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT,
        actor,
        repo,
        payload,
        createdAt
    )

    class PushGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: PushGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<PushGitHubPayload>(GitHubEventType.PUSH_EVENT, actor, repo, payload, createdAt)

    class ReleaseGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: ReleaseGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<ReleaseGitHubPayload>(GitHubEventType.RELEASE_EVENT, actor, repo, payload, createdAt)

    class SponsorshipGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: SponsorshipGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<SponsorshipGitHubPayload>(GitHubEventType.SPONSORSHIP_EVENT, actor, repo, payload, createdAt)

    class WatchGitHubEvent(
        actor: GitHubActor,
        repo: GitHubRepo,
        payload: WatchGitHubPayload,
        createdAt: LocalDateTime,
    ) : GitHubEvent<WatchGitHubPayload>(GitHubEventType.WATCH_EVENT, actor, repo, payload, createdAt)

    fun format(formatter: GitHubEventFormatter): String =
        formatter.format(this)
}