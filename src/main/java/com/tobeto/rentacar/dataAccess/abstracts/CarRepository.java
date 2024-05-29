package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.business.dtos.responses.CarDto;
import com.tobeto.rentacar.entities.concretes.Car;
import com.tobeto.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByPlate(String plate);
    //List<Car> findByModelName(String modelName);
    List<Car> findByModelName(@Param("modelName") String modelName);
}
