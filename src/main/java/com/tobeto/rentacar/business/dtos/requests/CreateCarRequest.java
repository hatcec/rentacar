package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.entities.concretes.Model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    private int modelId;
    @NotNull
    @Min(value = 1950)
    @Max(value = 2024)
    private int modelYear;
    @NotBlank
    private String plate;
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private int state;
    @Positive
    private double dailyPrice;
    private String imageUrl;

}
