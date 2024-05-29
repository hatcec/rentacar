package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdatedBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse addBrand (CreateBrandRequest createBrandRequest);
    List<GetAllBrandResponse> getAllBrand();
    UpdatedBrandResponse updateBrand(UpdateBrandRequest updateBrandRequest);
    void deleteBrand(int brandId);
    GetBrandByIdResponse getBrandById(  int id );
}
