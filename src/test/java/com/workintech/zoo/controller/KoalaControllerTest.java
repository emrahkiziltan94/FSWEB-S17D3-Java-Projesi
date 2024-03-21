package com.workintech.zoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.zoo.entity.Koala;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KoalaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class KoalaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Koala koala;

    @BeforeEach
     void setup() {

        koala = new Koala(1, "Kara", 20.0, 15.0, "Female");
    }

    @Test
    @Order(1)
     void testSaveKoala() throws Exception {
        mockMvc.perform(post("/koalas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(koala)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(koala.getId()))
                .andExpect(jsonPath("$.name").value(koala.getName()));
    }

    @Test
    @Order(2)
     void testFindAllKoalas() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));

        mockMvc.perform(get("/koalas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(koala.getId()));
    }

    @Test
    @Order(3)
     void testFindKoalaById() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));

        mockMvc.perform(get("/koalas/{id}", koala.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(koala.getId()));
    }

    @Test
    @Order(4)
     void testUpdateKoala() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));


        koala.setName("Updated Kara");
        mockMvc.perform(put("/koalas/{id}", koala.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(koala)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Kara"));
    }

    @Test
    @Order(5)
     void testDeleteKoala() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));

        mockMvc.perform(delete("/koalas/{id}", koala.getId()))
                .andExpect(status().isOk());
    }
}
