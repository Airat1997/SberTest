package com.sber.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sber.repository.ProductRepository;
import com.sber.service.ProductService;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RestApiControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private ProductService productRepository;
    String uuidString = "0bc1978c-a219-4ccc-adfc-3157910002df";
    UUID uuid = UUID.fromString(uuidString);
    String json = """
            {
                "description": "Test description",
                "id": "0bc1978c-a219-4ccc-adfc-3157910002df",
                "name": "Test",
                "price": 10000.0
            }
            """;
    String newJson = """
            {
                "description": "New Test description",
                "id": "0bc1978c-a219-4ccc-adfc-3157910002df",
                "name": "New Test",
                "price": 250.0
            }
            """;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestApiController(productRepository)).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/non_correct_path"))
                .andExpect(status().isNotFound());
    }

    @Test
    void postProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void putProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newJson))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}