package com.thoughtworks.capability.web;

import com.thoughtworks.capability.domain.Example;
import com.thoughtworks.capability.service.ExampleService;
import com.thoughtworks.capability.web.dto.CreateExampleRequest;
import com.thoughtworks.capability.web.dto.ExampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/examples")
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public ExampleResponse getExample(@PathVariable Long id) {
        Example example = exampleService.findExampleById(id);
        return ExampleResponse.builder()
            .id(example.getId())
            .description(example.getContent())
            .build();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ExampleResponse createExample(@RequestBody CreateExampleRequest createExampleRequest) {
        Example example = exampleService.createExample(createExampleRequest.toModel());
        return ExampleResponse.builder()
            .id(example.getId())
            .description(example.getContent())
            .build();
    }
}
