package ru.sug4chy.gson.typeadapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeTypeAdapter(
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
) : TypeAdapter<LocalDateTime>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalDateTime) {
        out.value(value.format(dateTimeFormatter))
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalDateTime =
        LocalDateTime.parse(`in`.nextString(), dateTimeFormatter)
}