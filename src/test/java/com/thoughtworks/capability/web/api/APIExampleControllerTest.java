package com.thoughtworks.capability.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.web.dto.CreateExampleRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class APIExampleControllerTest extends WebApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @Sql("/sql/api/insert_a_example.sql")
    public void shouldGetExampleByIdSuccessWhenExampleIsExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/examples/100")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(100));
    }

    @Test
    public void shouldCreateExampleSuccess() throws Exception {
        CreateExampleRequest request = new CreateExampleRequest("content");
        mvc.perform(MockMvcRequestBuilders
            .post("/examples")
            .content(new ObjectMapper().writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

}
