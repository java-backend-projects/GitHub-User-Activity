package ru.sug4chy.formatter

import ru.sug4chy.extensions.capitalizeFirstLetter
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubEvent.*
import ru.sug4chy.model.enums.GitHubEventType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GitHubEventFormatter {

    fun format(event: GitHubEvent<*>): String = when (event.type) {
        GitHubEventType.COMMIT_COMMENT_EVENT -> formatCommitComment(event as CommitCommentGitHubEvent)
        GitHubEventType.CREATE_EVENT -> formatCreate(event as CreateGitHubEvent)
        GitHubEventType.DELETE_EVENT -> formatDelete(event as DeleteGitHubEvent)
        GitHubEventType.FORK_EVENT -> formatFork(event as ForkGitHubEvent)
        GitHubEventType.GOLLUM_EVENT -> formatGollum(event as GollumGitHubEvent)
        GitHubEventType.ISSUE_COMMENT_EVENT -> formatIssueComment(event as IssueCommentGitHubEvent)
        GitHubEventType.ISSUES_EVENT -> formatIssues(event as IssuesGitHubEvent)
        GitHubEventType.MEMBER_EVENT -> TODO()
        GitHubEventType.PUBLIC_EVENT -> TODO()
        GitHubEventType.PULL_REQUEST_EVENT -> TODO()
        GitHubEventType.PULL_REQUEST_REVIEW_EVENT -> TODO()
        GitHubEventType.PULL_REQUEST_REVIEW_COMMENT_EVENT -> TODO()
        GitHubEventType.PULL_REQUEST_REVIEW_THREAD_EVENT -> TODO()
        GitHubEventType.PUSH_EVENT -> TODO()
        GitHubEventType.RELEASE_EVENT -> TODO()
        GitHubEventType.SPONSORSHIP_EVENT -> TODO()
        GitHubEventType.WATCH_EVENT -> TODO()
    }

    private fun formatCommitComment(event: CommitCommentGitHubEvent): String =
        "Commented on the commit in ${event.repoFullName} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatCreate(event: CreateGitHubEvent): String = if (event.payload.refType == "repository") {
        "Created a ${event.repoFullName} repository at ${formatCreationDateTime(event.createdAt)}"
    } else {
        "Created a ${event.payload.refType} in ${event.repoFullName} at ${formatCreationDateTime(event.createdAt)}"
    }

    private fun formatDelete(event: DeleteGitHubEvent): String =
        "Deleted ${event.payload.refType} in ${event.repoFullName} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatFork(event: ForkGitHubEvent): String =
        "Forked ${event.repoFullName} to ${event.payload.forkee.fullName} at ${formatCreationDateTime(event.createdAt)}"

    private fun formatGollum(event: GollumGitHubEvent): String {
        val createdPagesCount = event.payload.pages.count {
                it.action == "created"
            }
        val editedPagesCount = event.payload.pages.count {
                it.action == "edited"
            }

        return "Created $createdPagesCount and edited $editedPagesCount pages in ${event.repoFullName} wiki at ${
            formatCreationDateTime(
                event.createdAt
            )
        }"
    }

    private fun formatIssueComment(event: IssueCommentGitHubEvent): String {
        val performedAction = event.payload.action.capitalizeFirstLetter()
        val commentSource = if (event.payload.issue.isPullRequest()) "pull request" else "issue"

        return "$performedAction comment on the $commentSource in ${event.repoFullName} at ${
            formatCreationDateTime(
                event.createdAt
            )
        }"
    }

    private fun formatIssues(event: IssuesGitHubEvent): String {
        val performedAction = event.payload.action.capitalizeFirstLetter()
        val performedActionSubject = if (event.payload.issue.isPullRequest()) "pull request" else "issue"

        return "$performedAction the $performedActionSubject in ${event.repoFullName} at ${
            formatCreationDateTime(
                event.createdAt
            )
        }"
    }

    private fun formatCreationDateTime(dateTime: LocalDateTime): String =
        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}