package com.arjunan.springcloudbrewery.services.v2;

import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import com.arjunan.springcloudbrewery.web.modal.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {

    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    BeerDtoV2 updateBeer(UUID id, BeerDtoV2 beerDto);

    void deleteBeer(UUID id);
}
