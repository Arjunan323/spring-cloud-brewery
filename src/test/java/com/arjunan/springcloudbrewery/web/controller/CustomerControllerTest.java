package com.arjunan.springcloudbrewery.web.controller;

import com.arjunan.springcloudbrewery.services.CustomerService;
import com.arjunan.springcloudbrewery.web.modal.CustomerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper  objectMapper;

    static CustomerDto customerDto;

    @BeforeAll
    public static void setupCustomer(){
        customerDto = CustomerDto.builder()
                .id(null)
                .name("Customer 1")
                .build();
    }

    @Test
    void getCustomer() throws Exception {

        BDDMockito.given(customerService.getCustomerById(ArgumentMatchers.any(UUID.class))).willReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", CoreMatchers.is(customerDto.getId())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(customerDto.getName())));

    }

    @Test
    void handlePost() throws Exception {

        CustomerDto customerDto1 = customerDto;

        CustomerDto customerDto2 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Arjunan")
                .build();

        String customerString = objectMapper.writeValueAsString(customerDto2);

        BDDMockito.given(customerService.saveNewCustomer(ArgumentMatchers.any())).willReturn(customerDto2);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerString))
                .andExpect(status().isCreated());
    }

    @Test
    void handlePut() throws Exception {

        CustomerDto customerDto1 = customerDto;

        String customerJson = objectMapper.writeValueAsString(customerDto1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomer() {
    }
}