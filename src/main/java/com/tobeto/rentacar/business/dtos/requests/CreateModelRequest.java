package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateModelRequest {
    @NotBlank
    private String name;

    private int brandId;
    private int fuelId;
    private int transmissionId;
    private String imageUrl;
}
