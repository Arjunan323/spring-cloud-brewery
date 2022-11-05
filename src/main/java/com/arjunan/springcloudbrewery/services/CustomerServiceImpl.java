package com.arjunan.springcloudbrewery.services;

import com.arjunan.springcloudbrewery.web.modal.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Arjunan")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID id, CustomerDto customerDto) {
        // TODO
    }

    @Override
    public void deleteCustomer(UUID id) {
        // TODO
    }
}
