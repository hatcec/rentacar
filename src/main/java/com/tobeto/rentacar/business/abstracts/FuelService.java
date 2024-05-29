package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse addFuel(CreateFuelRequest createFuelRequest);
    List<GetAllFuelResponse> getAllFuel();
    UpdatedFuelResponse updateFuel(UpdateFuelRequest updateFuelRequest);
    void deleteFuel(int fuelId);
    GetFuelByIdResponse getFuelById(int id);
}
