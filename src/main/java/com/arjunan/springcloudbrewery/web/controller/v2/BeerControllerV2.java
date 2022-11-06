package com.arjunan.springcloudbrewery.web.controller.v2;

import com.arjunan.springcloudbrewery.services.BeerService;
import com.arjunan.springcloudbrewery.services.v2.BeerServiceV2;
import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import com.arjunan.springcloudbrewery.web.modal.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerService = beerServiceV2;
    }


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@NotNull @PathVariable(name = "beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<?> handlePost(@Valid @NotNull @RequestBody BeerDtoV2 beerDto){

        BeerDtoV2 savedBeerDto = beerService.saveNewBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname in url
        httpHeaders.add("Location" , "/api/v1/beer" + savedBeerDto.getId().toString());

        return new ResponseEntity<>(httpHeaders , HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> handlePut(@PathVariable(name = "beerId") UUID id, @Valid @RequestBody BeerDtoV2 beerDto){

        beerService.updateBeer(id,beerDto);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteBeer(@PathVariable(name = "beerId") UUID id){
        beerService.deleteBeer(id);
    }

}
