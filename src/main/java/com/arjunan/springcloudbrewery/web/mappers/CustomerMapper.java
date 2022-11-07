package com.arjunan.springcloudbrewery.web.mappers;

import com.arjunan.springcloudbrewery.domain.Customer;
import com.arjunan.springcloudbrewery.web.modal.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
