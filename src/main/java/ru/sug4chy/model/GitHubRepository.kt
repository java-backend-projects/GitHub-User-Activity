package ru.sug4chy.model

import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    @SerializedName("full_name")
    val fullName: String,
)
