package com.alura.foro_hub.validation;

import com.alura.foro_hub.DtoGetData.topics.DtoCreateTopicToDatabase;

public interface ValidationTopicRepository {
    public void checkValidation(DtoCreateTopicToDatabase dataTopic);
}
