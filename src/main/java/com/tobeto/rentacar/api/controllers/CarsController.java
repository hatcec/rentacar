package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.entities.concretes.Car;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
public class CarsController {
    private CarService carService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto addCar(@ModelAttribute @Valid @RequestBody CreateCarRequest createCarRequest) throws IOException {

        return carService.addCar(createCarRequest);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCar() {
        return carService.getAllCar();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarDto updateCar(@RequestBody() UpdateCarRequest updateCarRequest) {
        return carService.updateCar(updateCarRequest);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarDto getCarById(@PathVariable int id ){
        return carService.getCarById(id);
    }
//        @GetMapping(value = "/search{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public List<CarDto> getAllModelName(@PathVariable String name) {
//
//        return carService.getAllModelName(name);
//    }
@GetMapping("/cars")
public List<Car> getCarsByModelName(@RequestParam String modelName) {
    return carService.findCarsByModelName(modelName);
}
}
