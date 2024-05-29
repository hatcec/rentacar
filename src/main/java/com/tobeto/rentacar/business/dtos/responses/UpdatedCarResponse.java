package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedCarResponse {
    private int id;
    private int modelYear;
    private Model modelId;
    private String plate;
    private int state;
    private double dailyPrice;
    private LocalDateTime updatedDate;
    private  byte[] byteImage;
    private MultipartFile image;
}
