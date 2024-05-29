package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;

import java.util.List;

public interface ModelService {
    CreatedModelResponse addModel (CreateModelRequest createModelRequest);
    List<GetAllModelResponse> getAllModel();
    UpdatedModelResponse updateModel(UpdateModelRequest updateModelRequest);
    void deleteModel(int modelId);
    GetModelByIdResponse getModelById(int id);
    //List<GetAllModelResponse> getAllModelName(String  name);
}
