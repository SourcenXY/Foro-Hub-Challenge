package com.alura.foro_hub.DtoGetData.topics;

public record DtoUpdateResponse(
        String message,
        int idTopic,
        int idAuthor,
         String solution) {
}
