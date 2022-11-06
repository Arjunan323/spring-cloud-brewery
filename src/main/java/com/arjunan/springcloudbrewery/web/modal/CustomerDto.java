package com.arjunan.springcloudbrewery.web.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {

    private UUID id;

    @NotBlank(message = "name should not be blank")
    @Size(min = 3 , max = 100, message = "name should be greater than 3 and less than 100")
    private String name;
}
