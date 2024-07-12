package com.alura.foro_hub.DtoGetData.topics;

import com.alura.foro_hub.models.Topic;
import com.alura.foro_hub.models.User;

public record DtoCreateResponseToDatabase(
        String message,
        Topic topic,
        User author,
        String solution) {
}
