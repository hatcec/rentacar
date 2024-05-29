package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.*;
import com.tobeto.rentacar.business.dtos.responses.*;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse addTransmission(CreateTransmissionRequest createTransmissionRequest);
    List<GetAllTransmissionResponse> getAllTransmission();
    UpdatedTransmissionResponse updateTransmission(UpdateTransmissionRequest updateTransmissionRequest);
    void deleteTransmission(int transmissionId);
    GetTransmissionByIdResponse getTransmissionById(int id);
}
