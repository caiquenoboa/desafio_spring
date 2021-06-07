package com.mercadolivre.desafio_sring;

import com.mercadolivre.desafio_sring.dtos.postDTOs.request.PostCreateRequestDTO;
import com.mercadolivre.desafio_sring.dtos.productDTOs.request.ProductCreateRequestDTO;
import com.mercadolivre.desafio_sring.utils.ObjectToJson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(7)
    void testCreatePost() throws Exception {
        ProductCreateRequestDTO productCreateRequestDTO = new ProductCreateRequestDTO(
                "Cadeira Gamer",
                "Gamer",
                "Racer",
                "Red & Black",
                "Special Edition"
        );

        PostCreateRequestDTO postCreateRequestDTO = new PostCreateRequestDTO(
                2L,
                100L,
                1500.50,
                null,
                productCreateRequestDTO
        );

        mockMvc.perform(
                post("/products/newpost")
                        .content(ObjectToJson.convertString(postCreateRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
    }

    @Test
    @Order(8)
    void testGetFollowingPosts() throws Exception {
        mockMvc.perform(get("/products/followed/1/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].id_post").value(1));
    }
}
