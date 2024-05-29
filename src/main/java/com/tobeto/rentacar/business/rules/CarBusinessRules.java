package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import com.tobeto.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private CarRepository carRepository;

    public void plateNumberCanNotBeDuplicated(String plateNumber) {
        boolean car = carRepository.existsByPlate(plateNumber);
        if (car == true) {
            throw new BusinessException("PlateExists");
        }
    }
    //modelyear sınırı konulabilir.
}
