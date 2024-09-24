package ru.sug4chy.model

import ru.sug4chy.model.GitHubPayload.*
import ru.sug4chy.model.enums.GitHubEventType

sealed class GitHubEvent<out TPayload : GitHubPayload>(
    val type: GitHubEventType,
    val repo: GitHubRepo,
    val payload: TPayload
) {

    class CommitCommentGitHubEvent(
        repo: GitHubRepo,
        payload: CommitCommentGitHubPayload,
    ) : GitHubEvent<CommitCommentGitHubPayload>(GitHubEventType.COMMIT_COMMENT_EVENT, repo, payload)

    class CreateGitHubEvent(
        repo: GitHubRepo,
        payload: CreateGitHubPayload
    ) : GitHubEvent<CreateGitHubPayload>(GitHubEventType.CREATE_EVENT, repo, payload)

    class DeleteGitHubEvent(
        repo: GitHubRepo,
        payload: DeleteGitHubPayload
    ) : GitHubEvent<DeleteGitHubPayload>(GitHubEventType.DELETE_EVENT, repo, payload)

    class ForkGitHubEvent(
        repo: GitHubRepo,
        payload: ForkGitHubPayload,
    ) : GitHubEvent<ForkGitHubPayload>(GitHubEventType.FORK_EVENT, repo, payload)

    class GollumGitHubEvent(
        repo: GitHubRepo,
        payload: GollumGitHubPayload,
    ) : GitHubEvent<GollumGitHubPayload>(GitHubEventType.GOLLUM_EVENT, repo, payload)

    class IssueCommentGitHubEvent(
        repo: GitHubRepo,
        payload: IssueCommentGitHubPayload,
    ) : GitHubEvent<IssueCommentGitHubPayload>(GitHubEventType.ISSUE_COMMENT_EVENT, repo, payload)

    class IssuesGitHubEvent(
        repo: GitHubRepo,
        payload: IssuesGitHubPayload,
    ) : GitHubEvent<IssuesGitHubPayload>(GitHubEventType.ISSUES_EVENT, repo, payload)

    class MemberGitHubEvent(
        repo: GitHubRepo,
        payload: MemberGitHubPayload,
    ) : GitHubEvent<MemberGitHubPayload>(GitHubEventType.MEMBER_EVENT, repo, payload)

    class PublicGitHubEvent(
        repo: GitHubRepo,
        payload: PublicGitHubPayload,
    ) : GitHubEvent<PublicGitHubPayload>(GitHubEventType.PUBLIC_EVENT, repo, payload)

    class PullRequestGitHubEvent(
        repo: GitHubRepo,
        payload: PullRequestGitHubPayload,
    ) : GitHubEvent<PullRequestGitHubPayload>(GitHubEventType.PULL_REQUEST_EVENT, repo, payload)

    class PullRequestReviewGitHubEvent(
        repo: GitHubRepo,
        payload: PullRequestReviewGitHubPayload,
    ) : GitHubEvent<PullRequestReviewGitHubPayload>(GitHubEventType.PULL_REQUEST_REVIEW_EVENT, repo, payload)

    class PullRequestReviewCommentGitHubEvent(
        repo: GitHubRepo,
        payload: PullRequestReviewCommentGitHubPayload,
    ) : GitHubEvent<PullRequestReviewCommentGitHubPayload>(
        GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT,
        repo,
        payload
    )

    class PullRequestReviewThreadGitHubEvent(
        repo: GitHubRepo,
        payload: PullRequestReviewThreadGitHubPayload,
    ) : GitHubEvent<PullRequestReviewThreadGitHubPayload>(
        GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT,
        repo,
        payload
    )

    class PushGitHubEvent(
        repo: GitHubRepo,
        payload: PushGitHubPayload,
    ) : GitHubEvent<PushGitHubPayload>(GitHubEventType.PUSH_EVENT, repo, payload)

    class ReleaseGitHubEvent(
        repo: GitHubRepo,
        payload: ReleaseGitHubPayload,
    ) : GitHubEvent<ReleaseGitHubPayload>(GitHubEventType.RELEASE_EVENT, repo, payload)

    class SponsorshipGitHubEvent(
        repo: GitHubRepo,
        payload: SponsorshipGitHubPayload,
    ) : GitHubEvent<SponsorshipGitHubPayload>(GitHubEventType.SPONSORSHIP_EVENT, repo, payload)

    class WatchGitHubEvent(
        repo: GitHubRepo,
        payload: WatchGitHubPayload,
    ) : GitHubEvent<WatchGitHubPayload>(GitHubEventType.WATCH_EVENT, repo, payload)
}