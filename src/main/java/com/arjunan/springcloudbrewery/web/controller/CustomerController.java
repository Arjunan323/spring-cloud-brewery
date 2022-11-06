package com.arjunan.springcloudbrewery.web.controller;

import com.arjunan.springcloudbrewery.services.CustomerService;
import com.arjunan.springcloudbrewery.web.modal.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public  ResponseEntity<?> handlePost(@Valid @RequestBody CustomerDto  customerDto){

        CustomerDto savedCusterDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname in url
        httpHeaders.add("Location" , "/api/v1/customer" + savedCusterDto.getId().toString());

        return new ResponseEntity<>(httpHeaders , HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> handlePut(@PathVariable(name = "customerId") UUID id, @Valid @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(id,customerDto);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteCustomer(@PathVariable(name = "customerId") UUID id){
        customerService.deleteCustomer(id);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<?>> validationErrorHandler(ConstraintViolationException constraintViolationException){
        List<String> errors = new ArrayList<>(constraintViolationException.getConstraintViolations().size());

        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<?>> methodArgumentErrorHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errors = new ArrayList<>(methodArgumentNotValidException.getAllErrors().size());

        methodArgumentNotValidException.getAllErrors().forEach(objectError -> {
            errors.add(objectError.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
