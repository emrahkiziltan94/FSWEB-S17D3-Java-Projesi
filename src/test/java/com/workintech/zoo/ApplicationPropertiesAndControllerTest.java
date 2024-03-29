package com.workintech.zoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationPropertiesAndControllerTest {

    @Autowired
    private Environment env;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Kangaroo kangaroo;

    private Koala koala;

    @BeforeEach
    void setup() {

        kangaroo = new Kangaroo(1, "Kenny", 2.0, 85.0, "Male", false);
        koala = new Koala(1, "Kara", 20.0, 15.0, "Female");

    }


    @Test
    @DisplayName("application properties istenilenler eklendi mi?")
    void serverPortIsSetTo8585() {

        String serverPort = env.getProperty("server.port");
        assertThat(serverPort).isEqualTo("9000");

        String contextPath = env.getProperty("server.servlet.context-path");
        assertNotNull(contextPath);
        assertThat(contextPath).isEqualTo("/workintech");

    }


    @Test
    @DisplayName("KangarooController:SaveKangaroo")
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
    @DisplayName("KangarooController:FindAllKangaroos")
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
    @DisplayName("KangarooController:FindKangarooById")
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
    @DisplayName("KangarooController:UpdateKangaroo")
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
    @DisplayName("KangarooController:DeleteKangaroo")
    @Order(5)
    void testDeleteKangaroo() throws Exception {

        mockMvc.perform(post("/kangaroos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kangaroo)));


        mockMvc.perform(delete("/kangaroos/{id}", kangaroo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(kangaroo.getId()));
    }

    @Test
    @DisplayName("KoalaController:SaveKoala")
    @Order(6)
    void testSaveKoala() throws Exception {
        mockMvc.perform(post("/koalas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(koala)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(koala.getId()))
                .andExpect(jsonPath("$.name").value(koala.getName()));
    }

    @Test
    @DisplayName("KoalaController:FindAllKoalas")
    @Order(7)
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
    @DisplayName("KoalaController:FindKoalaById")
    @Order(8)
    void testFindKoalaById() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));

        mockMvc.perform(get("/koalas/{id}", koala.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(koala.getId()));
    }

    @Test
    @DisplayName("KoalaController:UpdateKoala")
    @Order(9)
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
    @DisplayName("KoalaController:DeleteKoala")
    @Order(10)
    void testDeleteKoala() throws Exception {

        mockMvc.perform(post("/koalas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(koala)));

        mockMvc.perform(delete("/koalas/{id}", koala.getId()))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("ZooGlobalExceptionHandler:HandleZooException")
    void testHandleZooException() throws Exception {

        int nonExistingId = 999;

        mockMvc.perform(get("/kangaroos/{id}", nonExistingId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    @DisplayName("ZooGlobalExceptionHandler:HandleGenericException")
    @Test
    void testHandleGenericException() throws Exception {


        Koala invalidKoala = new Koala();

        mockMvc.perform(post("/kangaroos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidKoala)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}


