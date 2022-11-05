package com.arjunan.springcloudbrewery.web.controller;

import com.arjunan.springcloudbrewery.services.BeerService;
import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Deprecated
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto>  getBeer(@PathVariable(name = "beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity handlePost(@RequestBody BeerDto beerDto){

        BeerDto savedBeerDto = beerService.saveNewBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO add hostname in url
        httpHeaders.add("Location" , "/api/v1/beer" + savedBeerDto.getId().toString());

        return new ResponseEntity(httpHeaders , HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity handlePut(@PathVariable(name = "beerId") UUID id, @RequestBody BeerDto beerDto){

        beerService.updateBeer(id,beerDto);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteBeer(@PathVariable(name = "beerId") UUID id){
        beerService.deleteBeer(id);
    }

}
