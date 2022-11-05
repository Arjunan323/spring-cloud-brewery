package com.arjunan.springcloudbrewery.services.v2;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements  BeerServiceV2{
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        return null;
    }

    @Override
    public void deleteBeer(UUID id) {

    }
}
