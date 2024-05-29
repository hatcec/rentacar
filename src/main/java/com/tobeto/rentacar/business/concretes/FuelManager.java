package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Fuel;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;
    private ModelMapperService modelMapperService;
    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse addFuel(CreateFuelRequest createFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(createFuelRequest.getName());
        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = this.fuelRepository.save(fuel);
        CreatedFuelResponse createdFuelResponse =
                this.modelMapperService.forResponse().map(createdFuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public List<GetAllFuelResponse> getAllFuel() {
        List<Fuel> models = fuelRepository.findAll();
        List<GetAllFuelResponse> fuelResponses = models.stream().map(fuel -> modelMapperService.forResponse()
                .map(fuel, GetAllFuelResponse.class)).collect(Collectors.toList());
        return fuelResponses;
    }

    @Override
    public UpdatedFuelResponse updateFuel(UpdateFuelRequest updateFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest().map(updateFuelRequest, Fuel.class);
        fuel.setUpdatedDate(LocalDateTime.now());
        Fuel updatedFuel = this.fuelRepository.save(fuel);
        UpdatedFuelResponse updatedFuelResponse =
                this.modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);
        return updatedFuelResponse;
    }

    @Override
    public GetFuelByIdResponse getFuelById(int id) {
        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not exists."));

        GetFuelByIdResponse response = modelMapperService.forResponse().map(fuel, GetFuelByIdResponse.class);
        return response;
    }

    @Override
    public void deleteFuel(int fuelId) {
        Fuel fuel = fuelRepository.getById(fuelId);
        fuelRepository.delete(fuel);
    }
}
