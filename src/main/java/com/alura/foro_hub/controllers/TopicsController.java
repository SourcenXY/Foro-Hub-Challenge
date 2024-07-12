package com.alura.foro_hub.controllers;

import com.alura.foro_hub.DtoGetData.topics.DtoCreateTopic;
import com.alura.foro_hub.DtoGetData.topics.DtoUpdateTopic;
import com.alura.foro_hub.DtoResponses.topics.DtoResponseGetDataTopic;
import com.alura.foro_hub.Services.ServiceTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicsController {
    @Autowired
    ServiceTopic serviceTopic;

    @GetMapping
    @Operation(summary = "Get the all topics", tags = "Get")
    public ResponseEntity getAllTopics() {
        return ResponseEntity.ok(serviceTopic.getAllDataTopic());
    }

    @PostMapping
    @Operation(summary = "Create a new topic", tags = "Post")
    public ResponseEntity createTopic(@RequestBody @Valid DtoCreateTopic dtoCreateTopic, UriComponentsBuilder uriComponentsBuilder) {
        DtoResponseGetDataTopic topicCreated = serviceTopic.createTopic(dtoCreateTopic);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicCreated.id()).toUri();

        return ResponseEntity.created(url).body(topicCreated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the information of topic by code", tags = "Get")
    public ResponseEntity getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceTopic.getTopicById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update the information about a topic", tags = "Put")
    public ResponseEntity updateTopicById(@PathVariable Long id, @RequestBody DtoUpdateTopic dtoUpdateTopic) {
        DtoResponseGetDataTopic dtoResponseGetDataTopic = serviceTopic.updateTopic(id, dtoUpdateTopic);

        return ResponseEntity.ok(dtoResponseGetDataTopic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a topic by Id", tags = "Delete")
    public ResponseEntity deleteTopicById(@PathVariable Long id) {
       return ResponseEntity.ok(serviceTopic.DeleteTopic(id));
    }
}
