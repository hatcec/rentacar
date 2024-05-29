package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBrandRequest {  //request ve response'ların içerisine validation anatasyonları konulur: bp
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
