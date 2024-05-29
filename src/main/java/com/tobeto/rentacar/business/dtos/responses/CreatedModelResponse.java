package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.entities.concretes.Brand;
import com.tobeto.rentacar.entities.concretes.Fuel;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedModelResponse {
    private int id;
    private String name;
    private Brand brandId;
    private Fuel fuelId;
    private Transmission transmissionId;
    private LocalDateTime createdDate;
}
