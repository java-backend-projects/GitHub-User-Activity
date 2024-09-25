package ru.sug4chy

import ru.sug4chy.apiclient.GitHubApiClient
import ru.sug4chy.apiclient.implementation.KtorGitHubApiClient
import ru.sug4chy.formatter.GitHubEventFormatter

suspend fun main(args: Array<String>) {
    if (args.isEmpty()) {
        handleInvalidInput()
        return
    }

    if (args[0] == "--help") {
        handleHelpCommand()
        return
    }

    val username: String = args[args.lastIndex]

    val client: GitHubApiClient = KtorGitHubApiClient()
    val events = client.listEventsForAuthenticatedUser(username)

    val formatter = GitHubEventFormatter()
    events.forEach { event ->
        println(event.format(formatter))
    }
}

private fun handleInvalidInput(): Unit =
    println(
        """
        "github-activity" requires 1 argument.
        See 'github-activity --help'.
        """.trimIndent()
    )

private fun handleHelpCommand(): Unit =
    println(
        """

        Usage: github-activity [OPTIONS] USERNAME

        Fetch resent activity on GitHub of user

        Options:
            --help                              Print usage
        """.trimIndent()
    )