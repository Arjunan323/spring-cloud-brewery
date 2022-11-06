package com.arjunan.springcloudbrewery.services.v2;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import com.arjunan.springcloudbrewery.web.modal.v2.BeerDtoV2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements  BeerServiceV2{
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public BeerDtoV2 updateBeer(UUID id, BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public void deleteBeer(UUID id) {

    }
}
