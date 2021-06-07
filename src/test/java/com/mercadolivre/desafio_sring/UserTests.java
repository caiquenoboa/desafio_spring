package com.mercadolivre.desafio_sring;

import com.mercadolivre.desafio_sring.dtos.userDTOs.request.UserCreateRequestDTO;
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
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testCreateUser() throws Exception {
        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO("cliente1", false);
        String json = ObjectToJson.convertString(userCreateRequestDTO);

        mockMvc.perform(
                post("/users")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("cliente1"));
    }

    @Test
    @Order(2)
    void testCreateSeller() throws Exception {
        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO("vendedor1", true);
        String json = ObjectToJson.convertString(userCreateRequestDTO);

        mockMvc.perform(
                post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("vendedor1"));
    }

    @Test
    @Order(3)
    void testCreateFollow() throws Exception {
        mockMvc.perform(post("/users/1/follow/2"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void testGetQuantitativeFollowers() throws Exception {
        mockMvc.perform(get("/users/2/followers/count"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("vendedor1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.followers_count").value(1));
    }

    @Test
    @Order(5)
    void testGetFollowersList() throws Exception {
        mockMvc.perform(get("/users/2/followers/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("vendedor1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.followers").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.followers[0].userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.followers[0].userName").value("cliente1"));
    }

    @Test
    @Order(6)
    void testGetFollowingList() throws Exception {
        mockMvc.perform(get("/users/1/followed/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("cliente1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.following").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.following[0].userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.following[0].userName").value("vendedor1"));
    }
}
