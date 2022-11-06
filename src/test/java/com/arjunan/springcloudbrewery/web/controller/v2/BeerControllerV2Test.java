package com.arjunan.springcloudbrewery.web.controller.v2;

import com.arjunan.springcloudbrewery.services.v2.BeerServiceV2;
import com.arjunan.springcloudbrewery.web.controller.BeerController;
import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import com.arjunan.springcloudbrewery.web.modal.v2.BeerDtoV2;
import com.arjunan.springcloudbrewery.web.modal.v2.BeerStyleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerControllerV2.class)
class BeerControllerV2Test {

    @MockBean
    BeerServiceV2 beerServiceV2;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    static BeerDtoV2 beerDto;

    @BeforeAll
    public static void setUp(){
        beerDto = BeerDtoV2.builder()
                .beerName("beer1")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(1224355L)
                .build();
    }

    @Test
    void getBeer() throws Exception {
        BDDMockito.given(beerServiceV2.getBeerById(any(UUID.class))).willReturn(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(beerDto.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beerName", CoreMatchers.is("beer1")));


    }

    @Test
    void handlePost() throws Exception {

        BeerDtoV2 beerDto1 = beerDto;

        beerDto1.setId(null);

        BeerDtoV2 saveBeer = BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName("New Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .build();

        String beerString = objectMapper.writeValueAsString(beerDto1);

        BDDMockito.given(beerServiceV2.saveNewBeer(any()))
                .willReturn(saveBeer);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v2/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerString))
                        .andExpect(status().isCreated());

    }

    @Test
    void handlePut() throws Exception {

        BeerDtoV2 beerDto1 = beerDto;

        beerDto1.setId(null);

        String beerDtoJson = objectMapper.writeValueAsString(beerDto1);

        //when
        mockMvc.perform(put("/api/v2/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerServiceV2).should().updateBeer(any(), any());


    }

    @Test
    void deleteBeer() {
    }
}