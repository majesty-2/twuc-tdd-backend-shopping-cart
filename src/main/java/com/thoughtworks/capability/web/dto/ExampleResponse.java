package com.thoughtworks.capability.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleResponse {
    private Long id;
    private String name;
    private Long age;
    private String avatar;
    private String description;
}
