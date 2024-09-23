package ru.sug4chy.model.enums;

public enum GitHubEventType {
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

    public static GitHubEventType fromPascalCaseString(String pascalCaseStringType) {
        char[] chars = pascalCaseStringType.toCharArray();
        StringBuilder sb = new StringBuilder(Character.toString(chars[0]));

        for (int i = 1; i < chars.length; i++) {

            if (Character.isUpperCase(chars[i])) {
                sb.append('_').append(chars[i]);
                continue;
            }

            sb.append(Character.toUpperCase(chars[i]));
        }

        return GitHubEventType.valueOf(sb.toString());
    }
}
