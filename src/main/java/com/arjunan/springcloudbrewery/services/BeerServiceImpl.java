package com.arjunan.springcloudbrewery.services;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements  BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Kingfisher")
                .beerStyle("Cooled")
                .upc(100L)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        //TODO
        return null;
    }

    @Override
    public void deleteBeer(UUID id) {
        // TODO
    }
}
