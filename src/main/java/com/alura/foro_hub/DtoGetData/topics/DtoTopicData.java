package com.alura.foro_hub.DtoGetData.topics;

import com.alura.foro_hub.models.Course;
import com.alura.foro_hub.models.Status;
import com.alura.foro_hub.models.User;

import java.time.LocalDateTime;

public record DtoTopicData (
        String title,
        String message,
        LocalDateTime creationdate,
        Status status,
        User author,
        Course course) {
}
