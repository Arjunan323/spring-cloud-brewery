package com.arjunan.springcloudbrewery.services.v2;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;

import java.util.UUID;

public interface BeerServiceV2 {

    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeer(UUID id);
}
