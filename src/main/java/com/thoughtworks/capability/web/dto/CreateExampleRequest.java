package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Example;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExampleRequest {
    String content;

    public Example toModel() {
        return Example.builder().content(content).build();
    }
}
