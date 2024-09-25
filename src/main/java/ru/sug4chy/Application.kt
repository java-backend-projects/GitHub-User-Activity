package ru.sug4chy

import ru.sug4chy.apiclient.GitHubApiClient
import ru.sug4chy.apiclient.implementation.KtorGitHubApiClient
import ru.sug4chy.formatter.GitHubEventFormatter

suspend fun main(args: Array<String>) {
    if (args.isEmpty()) {
        handleHelpCommand()
        return
    }

    if (args[0] == "--help") {
        handleHelpCommand()
        return
    }

    val perPageQueryParameter = if (args.contains("--per-page")) args[args.indexOf("--per_page") + 1].toInt() else null

    val username: String = args[args.lastIndex]

    val client: GitHubApiClient = KtorGitHubApiClient()
    val events = client.listEventsForAuthenticatedUser(username, perPageQueryParameter ?: 30)

    val formatter = GitHubEventFormatter()
    events.forEach { event ->
        println(event.format(formatter))
    }
}

private fun handleHelpCommand(): Unit =
    println(
        """
        Usage scheme: ./github-user-activity.sh <OPTIONS> [USERNAME]
        
        Options list:
        
        --per-page - Count of events. Default - 30
        --page - Number of page (number, not index). First page - 1, second page - 2 etc.
    """.trimIndent()
    )