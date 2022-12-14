package com.arjunan.springcloudbrewery.web.modal.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDtoV2 {

    @Null
    private UUID id;

    @NotBlank(message = "beerName should not be blank")
    private String beerName;

    private BeerStyleEnum beerStyle;

    @Positive
    private Long upc;
}
