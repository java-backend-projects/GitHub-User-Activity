package ru.sug4chy.model

import com.google.gson.annotations.SerializedName

data class GitHubActor(
    @SerializedName("display_login")
    val displayLogin: String
)