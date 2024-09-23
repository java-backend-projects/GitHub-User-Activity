package ru.sug4chy.model.events.create;

import com.google.gson.annotations.SerializedName;
import ru.sug4chy.model.abstractions.GitHubPayload;

public final class CreateGitHubPayload extends GitHubPayload {

    @SerializedName("ref_type")
    private String refType;
}