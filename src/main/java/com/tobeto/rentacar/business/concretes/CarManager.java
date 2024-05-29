package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private ModelRepository modelRepository;

    @Override
    public CarDto addCar(CreateCarRequest createCarRequest) throws IOException {
        carBusinessRules.plateNumberCanNotBeDuplicated(createCarRequest.getPlate());
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCreatedDate(LocalDateTime.now());
        Model model=modelRepository.findById(createCarRequest.getModelId()).orElseThrow();
        car.setModel(model);
        car.setId(0);
//        car.setImage(createCarRequest.getImageUrl().getBytes());
        return this.carRepository.save(car).getDto();
//        CreatedCarResponse createdCarResponse =
//                this.modelMapperService.forResponse().map(createdCar, CreatedCarResponse.class);
//        return createdCarResponse;

         /*setId(0) ataması, yeni bir varlık oluşturulduğunda kullanılır ve mevcut bir ID olmadığını belirtir.
         Spring Data JPA'nın save() yöntemi çağrıldığında, bu geçici ID, veritabanında gerçek bir ID ile
          değiştirilecektir.
         */
    }


    @Override
    public List<CarDto> getAllCar() {
        List<Car> cars = carRepository.findAll();
        List<CarDto> response = cars.stream().map(car -> modelMapperService.forResponse().map(car, CarDto.class)).collect(Collectors.toList());
        return response;
//        List<GetAllCarResponse> carResponses = cars.stream().map(car -> modelMapperService.forResponse()
//                .map(car, GetAllCarResponse.class)).toList();
      //  return cars.stream().map(Car::getDto).collect(Collectors.toList());
    }
    @Override
    public CarDto getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not exists."));

        CarDto response = modelMapperService.forResponse().map(car, CarDto.class);
        return response;
    }



    public List<Car> findCarsByModelName(String modelName) {
        return carRepository.findByModelName(modelName);
    }

    @Override
    public CarDto updateCar(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
        car.setUpdatedDate(LocalDateTime.now());
        Car updatedCar = this.carRepository.save(car);
        CarDto updatedCarResponse =
                this.modelMapperService.forResponse().map(updatedCar, CarDto.class);
        return updatedCarResponse;
    }

    @Override
    public void deleteCar(int carId) {
        Car car=carRepository.getById(carId);
        carRepository.delete(car);
    }

//    public List<Car> findCarsByModelName(String modelName) {
//
//    }
//        List<GetAllModelResponse> modelResponses = models.stream().map(model -> modelMapperService.forResponse()
//                .map(model, GetAllModelResponse.class)).toList();
//        return modelResponses;
//    }
}
