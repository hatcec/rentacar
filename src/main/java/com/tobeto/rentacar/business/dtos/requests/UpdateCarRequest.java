package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {
    private int id;
    private int modelYear;
    private String plate;
    private int state;
    private double dailyPrice;
    private Model modelId;
    private  byte[] byteImage;
    private MultipartFile image;

}
