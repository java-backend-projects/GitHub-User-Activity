package ru.sug4chy.apiclient.implementation

import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import ru.sug4chy.apiclient.GitHubApiClient
import ru.sug4chy.gson.typeadapters.GitHubEventsArrayTypeAdapter
import ru.sug4chy.model.GitHubEvent
import java.lang.reflect.Type

class KtorGitHubApiClient : GitHubApiClient {

    private val gitHubEventsArrayTypeAdapter = GitHubEventsArrayTypeAdapter()
    private val gitHubEventsArrayTypeToken: Type = object : TypeToken<Array<GitHubEvent<*>>>() {}.type

    override suspend fun listEventsForAuthenticatedUser(username: String, eventsCount: Int): Array<GitHubEvent<*>> =
        httpClient().use { client ->
            val httpResponse = client.get("https://api.github.com/users/$username/events") {
                headers {
                    append(HttpHeaders.Accept, "application/vnd.github+json")
                }
                url {
                    if (eventsCount > 0 && eventsCount != 30) {
                        parameter("per_page", eventsCount)
                    }
                }
            }

            return@use httpResponse.body()
        }

    private fun httpClient(): HttpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    registerTypeAdapter(gitHubEventsArrayTypeToken, gitHubEventsArrayTypeAdapter)
                }
            }
        }
}