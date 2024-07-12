package com.alura.foro_hub.DtoGetData.topics;

public record DtoCreateResponse (
        String message,
        int idTopic,
        int idAuthor,
        String solution) {
}
