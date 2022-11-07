package com.arjunan.springcloudbrewery.web.mappers;

import com.arjunan.springcloudbrewery.domain.Beer;
import com.arjunan.springcloudbrewery.web.modal.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(target = "lastUpdatedTime", source = "lastUpdateDate")
    @Mapping(target = "createdDateTime", source = "createdDate")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(target = "lastUpdateDate", source = "lastUpdatedTime")
    @Mapping(target = "createdDate", source = "createdDateTime")
    Beer beerDtoToBeer(BeerDto beerDto);
}
