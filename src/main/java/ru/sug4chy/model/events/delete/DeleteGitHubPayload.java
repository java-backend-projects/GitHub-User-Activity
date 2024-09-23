package ru.sug4chy.model.events.delete;

import com.google.gson.annotations.SerializedName;
import ru.sug4chy.model.abstractions.GitHubPayload;

public final class DeleteGitHubPayload extends GitHubPayload {

    @SerializedName("ref_type")
    private String refType;
}