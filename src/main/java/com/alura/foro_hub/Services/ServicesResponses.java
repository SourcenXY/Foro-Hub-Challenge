package com.alura.foro_hub.Services;

import com.alura.foro_hub.DtoGetData.topics.DtoCreateResponse;
import com.alura.foro_hub.DtoGetData.topics.DtoCreateResponseToDatabase;
import com.alura.foro_hub.DtoGetData.topics.DtoUpdateResponse;
import com.alura.foro_hub.DtoResponses.topics.DtoResponseDeleteResponse;
import com.alura.foro_hub.DtoResponses.topics.DtoResponseInfoResponse;
import com.alura.foro_hub.DtoResponses.topics.DtoResponsesInfoOfResponsesTopic;
import com.alura.foro_hub.databaseRepositories.ResponsesRepository;
import com.alura.foro_hub.databaseRepositories.TopicRepository;
import com.alura.foro_hub.databaseRepositories.UserRepository;
import com.alura.foro_hub.models.Responses;
import com.alura.foro_hub.models.Topic;
import com.alura.foro_hub.models.User;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesResponses {
    @Autowired
    ResponsesRepository responsesRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    public DtoResponseInfoResponse getResponseById(int responseId) {
        Optional<Responses> responseGetter = responsesRepository.findById(Long.valueOf(responseId));

        if(responseGetter.isEmpty()) {
            throw new ValidationException("The response does not exist, please check if the code is correct");
        }
        Responses response = responseGetter.get();
        DtoResponseInfoResponse dtoResponseInfoResponse = new DtoResponseInfoResponse(response.getCode(),
                response.getTopic().getCode(),
                response.getMessage(),
                response.getSolution(),
                response.getCreationdate(),
                response.getAuthor().getUsername());
        return dtoResponseInfoResponse;
    }

    public DtoResponsesInfoOfResponsesTopic createResponse(DtoCreateResponse dtoCreateResponse) {
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoCreateResponse.idAuthor()));
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(dtoCreateResponse.idTopic()));

        if(userGetter.isEmpty()) {
            throw new ValidationException("The code of the user does not exist, please check it");
        }

        if(topicGetter.isEmpty()) {
            throw new ValidationException("The topic does not exist, please check it");
        }

        DtoCreateResponseToDatabase dtoCreateResponseToDatabase = new DtoCreateResponseToDatabase(
                dtoCreateResponse.message(),
                topicGetter.get(),
                userGetter.get(),
                dtoCreateResponse.solution()
        );

        Responses response = new Responses(dtoCreateResponseToDatabase);
        responsesRepository.save(response);
        return fillData(topicGetter.get(), response);
    }

    public DtoResponsesInfoOfResponsesTopic updateResponse(Long idResponse, DtoUpdateResponse dtoUpdateResponse) {
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoUpdateResponse.idAuthor()));
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(dtoUpdateResponse.idTopic()));

        if(userGetter.isEmpty()) {
            throw new ValidationException("The code of the user does not exist, please check it");
        }

        if(topicGetter.isEmpty()) {
            throw new ValidationException("The topic does not exist, please check it");
        }
        Responses response = responsesRepository.findById(idResponse).get();
        response.setMessage(dtoUpdateResponse.message());
        response.setSolution(dtoUpdateResponse.solution());
        response.setAuthor(userGetter.get());
        response.setTopic(topicGetter.get());
        return fillData(topicGetter.get(), response);
    }

    public DtoResponseDeleteResponse deleteResponse(Long id) {
        try {
            DtoResponseDeleteResponse dtoResponseDeleteResponse = new DtoResponseDeleteResponse(200,
                    "The response was deleted correctly");
            responsesRepository.deleteById(id);
            return dtoResponseDeleteResponse;
        } catch (Exception e) {
            throw new ValidationException("It ocurred an error when you are deleting a response");
        }
    }

    public List<DtoResponsesInfoOfResponsesTopic> getResponsesByTopic(int topicId) {
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(topicId));
        List<DtoResponsesInfoOfResponsesTopic> listResponses = new ArrayList<>();

        if(topicGetter.isEmpty()) {
            throw new ValidationException("The code of the user does not exist, please check it");
        }
        Topic topic = topicGetter.get();
        List<Responses> responsesList =  responsesRepository.findByTopicId(topicId);

        for(Responses r: responsesList) {
            DtoResponsesInfoOfResponsesTopic dtoResponsesInfoOfResponsesTopic = fillData(topic, r);
            listResponses.add(dtoResponsesInfoOfResponsesTopic);
        }
        return listResponses;
    }

    public DtoResponsesInfoOfResponsesTopic fillData(Topic topic, Responses response) {
        DtoResponsesInfoOfResponsesTopic dtoResponsesInfoOfResponsesTopic = new DtoResponsesInfoOfResponsesTopic(
                topic.getCode(),
                topic.getMessage(),
                response.getCode(),
                response.getMessage(),
                response.getSolution(),
                response.getCreationdate(),
                response.getAuthor().getUsername()
                );
        return dtoResponsesInfoOfResponsesTopic;
    }
}