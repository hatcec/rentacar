package com.tobeto.rentacar.business.abstracts;


import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;

import com.tobeto.rentacar.business.dtos.requests.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.CarDto;
import com.tobeto.rentacar.entities.concretes.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    CarDto addCar(CreateCarRequest createCarRequest) throws IOException;
    List<CarDto> getAllCar();
    CarDto updateCar(UpdateCarRequest updateCarRequest);
    void deleteCar (int carId);
    CarDto getCarById(int id);
    List<Car> findCarsByModelName(String modelName);
}
