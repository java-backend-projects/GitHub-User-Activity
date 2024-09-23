package ru.sug4chy

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubPayload

// Будут 2 основные сущности:
// 1 - GitHubApiClient, будет внутри себя подготавливать параметры, отправлять запросы, получать ответы и десериализовывать их
// 2 - UserActivityAnalyzer, в который будет передаваться активность пользователя, полученная от API-клиента,
// а тут уже она будет печататься в терминал красиво
// План создания проекта:
// 1. Создать классы моделей для десериализации, ориентируясь на документацию к API GitHub +
// 2. Добавить нужные TypeAdapter-ы для десериализации ответа от API GitHub
// 3. Создать 1-ю основную сущность проекта - GitHubApiClient, который будет работать поверх OkHttp3
// 4. Создать 2-ю основную сущность проекта - USerActivityAnalyzer, предварительно продумав форматы сообщений,
// которые будут выводиться в терминале

suspend fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please, enter GitHub username.")
        return
    }

    val username = args[0]

    val client: HttpClient = setupHttpClient()
    val gitHubApiResponse: HttpResponse = client.get("https://api.github.com/users/$username/events")

    val events: List<GitHubEvent<GitHubPayload>> = Json.decodeFromJsonElement(gitHubApiResponse.body())

    println(events)

    client.close()
}

private fun setupHttpClient(): HttpClient =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }