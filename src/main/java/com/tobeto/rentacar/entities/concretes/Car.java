package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.business.dtos.responses.CarDto;
import com.tobeto.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cars")
public class Car  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int  id;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
    @Column(name = "deleteDate")
    private LocalDateTime deletedDate;
    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "plate")
    private String plate;

    @Column(name = "state") //1-Avaible 2-Rented 3-Under Maintenance
    private int state;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @ManyToOne()
    @JoinColumn(name = "model_id")
    private Model model;


//    @Lob
//    @Column(columnDefinition = "bytea")
//    private  byte[] image;

    public CarDto getDto(){
        CarDto carDto=new CarDto();
        carDto.setId(id);
        carDto.setCreatedDate(createdDate);
        carDto.setModelYear(modelYear);
        carDto.setPlate(plate);
        carDto.setState(state);
        carDto.setDailyPrice(dailyPrice);
        carDto.setModelId(carDto.getModelId());
        carDto.setModelName(carDto.getModelName());
        return carDto;
    }
}
