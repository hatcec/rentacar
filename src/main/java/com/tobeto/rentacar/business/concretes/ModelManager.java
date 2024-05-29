package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.business.rules.ModelBusinessRules;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import com.tobeto.rentacar.entities.concretes.Fuel;
import com.tobeto.rentacar.entities.concretes.Model;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private FuelBusinessRules fuelBusinessRules;
    private TransmissionBusinessRules transmissionBusinessRules;
    private BrandBusinessRules brandBusinessRules;
    private FuelRepository fuelRepository;
    private TransmissionRepository transmissionRepository;
    private BrandRepository brandRepository;

    @Override
    public CreatedModelResponse addModel(CreateModelRequest createModelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(createModelRequest.getName());
        fuelBusinessRules.isFuelExists(createModelRequest.getFuelId());
        transmissionBusinessRules.isTransmissionExists(createModelRequest.getTransmissionId());
        brandBusinessRules.isBrandExists(createModelRequest.getBrandId());
        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());
        Fuel fuel=fuelRepository.findById(createModelRequest.getFuelId()).orElseThrow();
        Brand brand=brandRepository.findById(createModelRequest.getTransmissionId()).orElseThrow();
        Transmission transmission=transmissionRepository.findById(createModelRequest.getTransmissionId()).orElseThrow();
        model.setFuel(fuel);
        model.setBrand(brand);
        model.setTransmission(transmission);
        Model createdModel = this.modelRepository.save(model);
        CreatedModelResponse createdModelResponse =
                this.modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
        return createdModelResponse;
    }


    @Override
    public List<GetAllModelResponse> getAllModel() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = models.stream().map(model -> modelMapperService.forResponse()
                .map(model, GetAllModelResponse.class)).collect(Collectors.toList());
        return modelResponses;
    }

    @Override
    public UpdatedModelResponse updateModel(UpdateModelRequest updateModelRequest) {
        Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
        model.setUpdatedDate(LocalDateTime.now());
        Model updatedModel = this.modelRepository.save(model);
        UpdatedModelResponse updatedModelResponse =
                this.modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    public void deleteModel(int modelId) {
        Model model = modelRepository.getById(modelId);
        modelRepository.delete(model);
    }
    @Override
    public GetModelByIdResponse getModelById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not exists."));

        GetModelByIdResponse response = modelMapperService.forResponse().map(model, GetModelByIdResponse.class);
        return response;
    }



}
