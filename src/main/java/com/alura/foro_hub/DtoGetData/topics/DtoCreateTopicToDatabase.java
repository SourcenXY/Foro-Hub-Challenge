package com.alura.foro_hub.DtoGetData.topics;

import com.alura.foro_hub.models.Course;
import com.alura.foro_hub.models.Status;
import com.alura.foro_hub.models.User;

public record DtoCreateTopicToDatabase (
        String title,
        String message,
        User user,
        Course course,
        Status status) {
}
