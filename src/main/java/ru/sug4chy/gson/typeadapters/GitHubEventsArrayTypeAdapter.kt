package ru.sug4chy.gson.typeadapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ru.sug4chy.model.GitHubEvent
import java.io.IOException

class GitHubEventsArrayTypeAdapter(
    private val eventAdapter: GitHubEventTypeAdapter = GitHubEventTypeAdapter()
) : TypeAdapter<Array<GitHubEvent<*>>>() {

    @Throws(UnsupportedOperationException::class)
    override fun write(out: JsonWriter, value: Array<GitHubEvent<*>>): Nothing =
        throw UnsupportedOperationException("GitHubEventsArrayTypeAdapter is intended only for deserialization.")

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Array<GitHubEvent<*>> {
        val events: MutableList<GitHubEvent<*>> = mutableListOf()

        `in`.beginArray()
        while (`in`.hasNext()) {
            events.add(
                eventAdapter.read(`in`)
            )
        }
        `in`.endArray()

        return events.toTypedArray()
    }
}