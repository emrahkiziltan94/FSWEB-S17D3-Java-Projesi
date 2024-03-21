package com.workintech.zoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.zoo.entity.Kangaroo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KangarooController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KangarooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Kangaroo kangaroo;

    @BeforeEach
    void setup() {

        kangaroo = new Kangaroo(1, "Kenny", 2.0, 85.0, "Male", false);

    }

    @Test
    @Order(1)
    void testSaveKangaroo() throws Exception {
        mockMvc.perform(post("/kangaroos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kangaroo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kangaroo.getId()))
                .andExpect(jsonPath("$.name").value(kangaroo.getName()));
    }

    @Test
    @Order(2)
    void testFindAllKangaroos() throws Exception {

        mockMvc.perform(post("/kangaroos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kangaroo)));

        mockMvc.perform(get("/kangaroos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(kangaroo.getId()));
    }

    @Test
    @Order(3)
    void testFindKangarooById() throws Exception {

        mockMvc.perform(post("/kangaroos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kangaroo)));

        mockMvc.perform(get("/kangaroos/{id}", kangaroo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kangaroo.getId()));
    }

    @Test
    @Order(4)
    void testUpdateKangaroo() throws Exception {

        mockMvc.perform(post("/kangaroos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kangaroo)));


        kangaroo.setName("Updated Kenny");
        mockMvc.perform(put("/kangaroos/{id}", kangaroo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kangaroo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Kenny"));
    }

    @Test
    @Order(5)
    void testDeleteKangaroo() throws Exception {

        mockMvc.perform(post("/kangaroos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kangaroo)));


        mockMvc.perform(delete("/kangaroos/{id}", kangaroo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kangaroo.getId()));
    }
}
