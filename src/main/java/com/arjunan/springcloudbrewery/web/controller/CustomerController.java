package com.arjunan.springcloudbrewery.web.controller;

import com.arjunan.springcloudbrewery.services.CustomerService;
import com.arjunan.springcloudbrewery.web.modal.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable(name = "customerId") UUID customerId){
        return new ResponseEntity<>(this.customerService.getCustomerById(customerId) , HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity handlePost(@RequestBody CustomerDto  customerDto){

        CustomerDto savedCusterDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname in url
        httpHeaders.add("Location" , "/api/v1/customer" + savedCusterDto.getId().toString());

        return new ResponseEntity(httpHeaders , HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handlePut(@PathVariable(name = "customerId") UUID id, @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(id,customerDto);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteCustomer(@PathVariable(name = "customerId") UUID id){
        customerService.deleteCustomer(id);
    }

}
