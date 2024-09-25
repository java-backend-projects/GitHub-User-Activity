package ru.sug4chy.formatter

import ru.sug4chy.extensions.capitalizeFirstLetter
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubEvent.*
import ru.sug4chy.model.enums.GitHubEventType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GitHubEventFormatter {

    internal fun format(event: GitHubEvent<*>): String = when (event.type) {
        GitHubEventType.COMMIT_COMMENT_EVENT -> formatCommitComment(event as CommitCommentGitHubEvent)
        GitHubEventType.CREATE_EVENT -> formatCreate(event as CreateGitHubEvent)
        GitHubEventType.DELETE_EVENT -> formatDelete(event as DeleteGitHubEvent)
        GitHubEventType.FORK_EVENT -> formatFork(event as ForkGitHubEvent)
        GitHubEventType.GOLLUM_EVENT -> formatGollum(event as GollumGitHubEvent)
        GitHubEventType.ISSUE_COMMENT_EVENT -> formatIssueComment(event as IssueCommentGitHubEvent)
        GitHubEventType.ISSUES_EVENT -> formatIssues(event as IssuesGitHubEvent)
        GitHubEventType.MEMBER_EVENT -> formatMember(event as MemberGitHubEvent)
        GitHubEventType.PUBLIC_EVENT -> formatPublic(event as PublicGitHubEvent)
        GitHubEventType.PULL_REQUEST_EVENT -> formatPullRequest(event as PullRequestGitHubEvent)
        GitHubEventType.PULL_REQUEST_REVIEW_EVENT -> formatPullRequestReview(event as PullRequestReviewGitHubEvent)
        GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT -> formatPullRequestReviewComment(event as PullRequestReviewCommentGitHubEvent)
        GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT -> formatPullRequestReviewThread(event as PullRequestReviewThreadGitHubEvent)
        GitHubEventType.PUSH_EVENT -> formatPush(event as PushGitHubEvent)
        GitHubEventType.RELEASE_EVENT -> formatRelease(event as ReleaseGitHubEvent)
        GitHubEventType.SPONSORSHIP_EVENT -> formatSponsorship(event as SponsorshipGitHubEvent)
        GitHubEventType.WATCH_EVENT -> formatWatch(event as WatchGitHubEvent)
    }

    private fun formatCommitComment(event: CommitCommentGitHubEvent): String =
        "Commented on the commit in ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatCreate(event: CreateGitHubEvent): String = if (event.payload.refType == "repository") {
        "Created a ${event.repo.name} repository at ${formatCreationDateTime(event.createdAt)}"
    } else {
        "Created a ${event.payload.refType} in ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"
    }

    private fun formatDelete(event: DeleteGitHubEvent): String =
        "Deleted ${event.payload.refType} in ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatFork(event: ForkGitHubEvent): String =
        "Forked ${event.repo.name} to ${event.payload.forkee.fullName} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatGollum(event: GollumGitHubEvent): String {
        val createdPagesCount = event.payload.pages.count {
            it.action == "created"
        }
        val editedPagesCount = event.payload.pages.count {
            it.action == "edited"
        }

        return "Created $createdPagesCount and edited $editedPagesCount pages in ${event.repo.name} wiki at ${
            formatCreationDateTime(event.createdAt)
        }"
    }

    private fun formatIssueComment(event: IssueCommentGitHubEvent): String {
        val performedAction = event.payload.action.capitalizeFirstLetter()
        val commentSource = if (event.payload.issue.isPullRequest()) "pull request" else "issue"

        return "$performedAction comment on the $commentSource in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"
    }

    private fun formatIssues(event: IssuesGitHubEvent): String {
        val performedAction = event.payload.action.capitalizeFirstLetter()
        val performedActionSubject = if (event.payload.issue.isPullRequest()) "pull request" else "issue"

        return "$performedAction the $performedActionSubject in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"
    }

    private fun formatMember(event: MemberGitHubEvent): String =
        "Added collaborator to ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatPublic(event: PublicGitHubEvent): String =
        "Made repository ${event.repo.name} public at ${formatCreationDateTime(event.createdAt)}"

    private fun formatPullRequest(event: PullRequestGitHubEvent): String =
        "${event.payload.action.capitalizeFirstLetter()} pull request in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"

    private fun formatPullRequestReview(event: PullRequestReviewGitHubEvent): String =
        "Reviewed pull request in ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatPullRequestReviewComment(event: PullRequestReviewCommentGitHubEvent): String =
        "${event.payload.action.capitalizeFirstLetter()} comment on pull request review in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"

    private fun formatPullRequestReviewThread(event: PullRequestReviewThreadGitHubEvent): String =
        "${event.payload.action.capitalizeFirstLetter()} thread on pull request review in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"

    private fun formatPush(event: PushGitHubEvent): String =
        "Pushed ${event.payload.commits.size} ${if (event.payload.commits.size > 1) "commits" else "commit"} " +
                "to the ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatRelease(event: ReleaseGitHubEvent): String =
        "${event.payload.action.capitalizeFirstLetter()} release in ${event.repo.name} at ${
            formatCreationDateTime(event.createdAt)
        }"

    private fun formatSponsorship(event: SponsorshipGitHubEvent): String =
        "${event.payload.action} sponsorship in ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatWatch(event: WatchGitHubEvent): String =
        "Starred ${event.repo.name} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatCreationDateTime(dateTime: LocalDateTime): String =
        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}