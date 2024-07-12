package com.alura.foro_hub.DtoResponses.topics;

import com.alura.foro_hub.DtoResponses.user.DtoUser;

import java.time.LocalDateTime;

public record DtoResponseTopic (
        LocalDateTime creationDate,
        String message,
        String solution,
        DtoUser author) {
}
