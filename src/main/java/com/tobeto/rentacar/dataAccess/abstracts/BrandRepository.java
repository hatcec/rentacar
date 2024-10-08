package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findByNameIgnoreCase(String name);//findBy: reserved kelimedir. Ignore case büyük-küçük harf duyarlılığı yapma
    boolean existsById(int brandId);
}
