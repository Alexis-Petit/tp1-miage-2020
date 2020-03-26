package com.acme.todolist.adapters.rest_api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.acme.todolist.domain.TodoItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@ActiveProfiles
public class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPostLateMessage() throws Exception {
        Instant notLate = Instant.now();
        TodoItem todo = new TodoItem("2", notLate, "this todo is not late");
        this.mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(todo)))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
}

