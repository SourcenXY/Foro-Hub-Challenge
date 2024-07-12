package com.alura.foro_hub.validation.topic;

import com.alura.foro_hub.DtoGetData.topics.DtoCreateTopicToDatabase;
import com.alura.foro_hub.validation.ValidationTopicRepository;
import com.alura.foro_hub.databaseRepositories.TopicRepository;
import com.alura.foro_hub.models.Topic;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validateNoEqualTitleOrMessage implements ValidationTopicRepository {
    @Autowired
    TopicRepository topicRepository;

    @Override
    public void checkValidation(DtoCreateTopicToDatabase dataTopic) {
        Topic topicData = topicRepository.findTopicByTitleOrMessage(dataTopic.title(), dataTopic.message());

        if(topicData != null) {
            throw new ValidationException("The topic exists with title or message description");
        }
    }
}
