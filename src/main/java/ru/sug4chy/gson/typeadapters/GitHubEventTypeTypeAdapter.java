package ru.sug4chy.gson.typeadapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.sug4chy.model.enums.GitHubEventType;

import java.io.IOException;

public final class GitHubEventTypeTypeAdapter extends TypeAdapter<GitHubEventType> {
    @Override
    public void write(JsonWriter out, GitHubEventType value) throws IOException {
        throw new UnsupportedOperationException("GitHubEventTypeTypeAdapter is intended only for deserialization.");
    }

    @Override
    public GitHubEventType read(JsonReader in) throws IOException {
        return GitHubEventType.fromPascalCaseString(in.nextString());
    }
}