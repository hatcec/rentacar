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
public class GetAllModelResponse {
    private int id;
    private String name;
    private int brandId;
    private String brandName;
    private int fuelId;
    private String fuelName;
    private int transmissionId;
    private String transmissionName;
    private LocalDateTime createdDate;
    private String imageUrl;
}
