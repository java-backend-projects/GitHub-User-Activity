package ru.sug4chy.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class GitHubEventType {
    COMMIT_COMMENT_EVENT,
    CREATE_EVENT,
    DELETE_EVENT,
    FORK_EVENT,
    GOLLUM_EVENT,
    ISSUE_COMMENT_EVENT,
    ISSUES_EVENT,
    MEMBER_EVENT,
    PUBLIC_EVENT,
    PULL_REQUEST_EVENT,
    PULL_REQUEST_REVIEW_EVENT,
    PULL_REQUEST_REVIEW_COMMENT_EVENT,
    PULL_REQUEST_REVIEW_THREAD_EVENT,
    PUSH_EVENT,
    RELEASE_EVENT,
    SPONSORSHIP_EVENT,
    WATCH_EVENT;

    companion object {

        fun fromPascalCaseString(pascalCaseStringType: String): GitHubEventType {
            val chars = pascalCaseStringType.toCharArray()
            val sb = StringBuilder(chars[0].toString())

            for (i in 1 until chars.size) {
                if (chars[i].isUpperCase()) {
                    sb.append('_').append(chars[i])
                    continue
                }

                sb.append(chars[i].uppercaseChar())
            }

            return valueOf(sb.toString())
        }
    }
}