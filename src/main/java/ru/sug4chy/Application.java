package ru.sug4chy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sug4chy.gson.typeadapters.GitHubEventTypeTypeAdapter;
import ru.sug4chy.model.enums.GitHubEventType;

// Будут 2 основные сущности:
// 1 - GitHubApiClient, будет внутри себя подготавливать параметры, отправлять запросы, получать ответы и десериализовывать их
// 2 - UserActivityAnalyzer, в который будет передаваться активность пользователя, полученная от API-клиента,
// а тут уже она будет печататься в терминал красиво

// План создания проекта:
// 1. Создать классы моделей для десериализации, ориентируясь на документацию к API GitHub
// 2. Добавить нужные TypeAdapter-ы для десериализации ответа от API GitHub
// 3. Создать 1-ю основную сущность проекта - GitHubApiClient, который будет работать поверх OkHttp3
// 4. Создать 2-ю основную сущность проекта - USerActivityAnalyzer, предварительно продумав форматы сообщений,
// которые будут выводиться в терминале

public class Application {

    private static Gson gson;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please, enter GitHub username.");
            return;
        }

        initServices();
    }

    private static void initServices() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(GitHubEventType.class, new GitHubEventTypeTypeAdapter())
                .create();
    }
}