package ru.sug4chy

import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import ru.sug4chy.gson.typeadapters.GitHubEventsArrayTypeAdapter
import ru.sug4chy.model.GitHubEvent
import ru.sug4chy.model.GitHubPayload

// Будут 2 основные сущности:
// 1 - GitHubApiClient, будет внутри себя подготавливать параметры, отправлять запросы, получать ответы и десериализовывать их
// 2 - UserActivityAnalyzer, в который будет передаваться активность пользователя, полученная от API-клиента,
// а тут уже она будет печататься в терминал красиво
// План создания проекта:
// 1. Создать классы моделей для десериализации, ориентируясь на документацию к API GitHub +
// 2. Добавить нужные TypeAdapter-ы для десериализации ответа от API GitHub +
// 3. Создать 1-ю основную сущность проекта - GitHubApiClient, который будет работать поверх Ktor.Client
// 4. Создать 2-ю основную сущность проекта - UserActivityAnalyzer, предварительно продумав форматы сообщений,
// которые будут выводиться в терминале

suspend fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please, enter GitHub username.")
        return
    }

    if (args[0] == "--help") {
        handleHelpCommand()
        return
    }

    val perPageQueryParameter = if (args.contains("--per_page")) args[args.indexOf("--per_page") + 1].toInt() else null
    val pageQueryParameter = if (args.contains("--page")) args[args.indexOf("--page") + 1].toInt() else null

    val username: String = args.last()
    httpClient().use {
        val response: Array<GitHubEvent<*>> = it.get("https://api.github.com/users/$username/events") {
            headers {
                append(HttpHeaders.Accept, "application/vnd.github+json")
            }
            url {
                if (perPageQueryParameter != null) parameter("per_page", perPageQueryParameter)
                if (pageQueryParameter != null) parameter("page", pageQueryParameter)
            }
        }.body()

        println(response.size)

        for (event in response) {
            println(event.type)
        }
    }
}

private fun httpClient(): HttpClient =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                registerTypeAdapter(object : TypeToken<Array<GitHubEvent<*>>>() {}.type, GitHubEventsArrayTypeAdapter())
            }
        }
    }

private fun handleHelpCommand(): Unit =
    println("""
        Usage scheme: ./github-user-activity.sh <OPTIONS> [USERNAME]
        
        Options list:
        
        --per_page - Count of events. Default - 30
        --page - Number of page (number, not index). First page - 1, second page - 2 etc.
    """.trimIndent()
    )