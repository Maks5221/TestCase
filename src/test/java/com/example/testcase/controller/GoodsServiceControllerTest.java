package com.example.testcase.controller;

import com.example.testcase.entity.Goods;
import com.example.testcase.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoodsController.class)
public class GoodsServiceControllerTest {

    @MockBean
    private GoodsService goodsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void create() throws Exception {
        Goods goods = new Goods(1L, "Milk", "Coconut milk", 95, "Available");
        String goodsJson = objectMapper.writeValueAsString(goods);
        mockMvc.perform(post("/goods/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(goodsJson))
                .andExpect(status().isOk());
    }

    @Test
    void read() throws Exception {
        Goods goods = new Goods(1L, "Milk", "Coconut milk", 95, "Available");
        Goods goods1 = new Goods(1L, "Beef", "Fresh", 282, "Not available");
        when(goodsService.getAll()).thenReturn(List.of(goods, goods1));
        mockMvc.perform(get("/goods/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getGoodsById() throws Exception {
        Goods goods = new Goods(1L, "Milk", "Coconut milk", 95, "Available");
        when(goodsService.getGoodsById(1L)).thenReturn(goods);
        mockMvc.perform(get("/goods/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Milk"))
                .andExpect(jsonPath("$.description").value("Coconut milk"))
                .andExpect(jsonPath("$.price").value(95))
                .andExpect(jsonPath("$.availability").value("Available"));
    }

}
