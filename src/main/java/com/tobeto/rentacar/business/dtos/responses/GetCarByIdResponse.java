package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarByIdResponse {
    private int id;
    private int modelYear;
    private String plate;
    private int state;
    private double dailyPrice;
    private int modelId;
    private String modelName;
    private int brandId;
    private  String brandName;
    private int fuelId;
    private String fuelName;
    private int trasnmissionId;
    private String transmissionName;
    private LocalDateTime createdDate;
    private  byte[] byteImage;
    private MultipartFile image;

}
