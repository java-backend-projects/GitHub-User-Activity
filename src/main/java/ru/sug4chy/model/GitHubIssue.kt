package ru.sug4chy.model;

import com.google.gson.annotations.SerializedName;

public class GitHubIssue {

    @SerializedName("pull_request")
    private Object pullRequest;

    public boolean isIssue() {
        return pullRequest == null;
    }

    public boolean isPullRequest() {
        return pullRequest != null;
    }
}
