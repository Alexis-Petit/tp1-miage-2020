package com.acme.todolist.adapters.rest_api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.acme.todolist.application.port.in.AddTodoItems;
import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = TodoListController.class)
@WebMvcTest(controllers = TodoListController.class)
@AutoConfigureMockMvc
public class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AddTodoItems addTodoItemsQuery;
    
    @MockBean
    private GetTodoItems getTodoItemsQuery;
    

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

