package com.arjunan.springcloudbrewery.services;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import com.arjunan.springcloudbrewery.web.modal.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    public CustomerDto getCustomerById(UUID id);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID id, CustomerDto customerDto);

    void deleteCustomer(UUID id);
}
