package com.alura.foro_hub.DtoResponses.topics;

import java.time.LocalDateTime;

public record DtoResponseInfoResponse
        (Integer codeResponse,
         Integer codeTopic,
         String message,
         String solution,
         LocalDateTime creationDate,
         String usernameAuthor
         ) {
}
