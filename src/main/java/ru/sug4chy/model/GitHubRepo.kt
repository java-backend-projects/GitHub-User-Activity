package ru.sug4chy.model

import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepo (
    val name: String
)