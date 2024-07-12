package com.alura.foro_hub.DtoResponses.topics;

import com.alura.foro_hub.DtoResponses.user.DtoUser;

import java.time.LocalDateTime;
import java.util.List;

public record DtoResponseGetDataTopic(
        Integer id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        DtoUser user,
        String course,
        List<DtoResponseTopic> listResponses) {
}
