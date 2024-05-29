package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;
    private TransmissionBusinessRules transmissionBusinessRules;
    @Override
    public CreatedTransmissionResponse addTransmission(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(createTransmissionRequest.getName());
        Transmission transmission = this.modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = this.transmissionRepository.save(transmission);
        CreatedTransmissionResponse createdTransmissionResponse =
                this.modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public List<GetAllTransmissionResponse> getAllTransmission() {
        List<Transmission> transmissions =transmissionRepository.findAll();
        List<GetAllTransmissionResponse> transmissionResponses = transmissions.stream().map(transmission -> modelMapperService.forResponse()
                .map(transmission, GetAllTransmissionResponse.class)).collect(Collectors.toList());
        return transmissionResponses;
    }

    @Override
    public UpdatedTransmissionResponse updateTransmission(UpdateTransmissionRequest updateTransmissionRequest) {
        Transmission transmission = this.modelMapperService.forRequest().map(updateTransmissionRequest, Transmission.class);
        transmission.setUpdatedDate(LocalDateTime.now());
        Transmission updatedTransmission = this.transmissionRepository.save(transmission);
        UpdatedTransmissionResponse updatedTransmissionResponse =
                this.modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
        return updatedTransmissionResponse;
    }
    @Override
    public GetTransmissionByIdResponse getTransmissionById(int id) {
        Transmission transmission = transmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transmission not exists."));

        GetTransmissionByIdResponse response = modelMapperService.forResponse().map(transmission, GetTransmissionByIdResponse.class);

        return response;
    }
    @Override
    public void deleteTransmission(int transmissionId) {
        Transmission transmission=transmissionRepository.getById(transmissionId);
        transmissionRepository.delete(transmission);
    }
}
