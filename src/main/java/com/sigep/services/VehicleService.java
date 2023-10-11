package com.sigep.services;

import com.sigep.dtos.VehicleRecordDto;
import com.sigep.models.VehicleModel;
import com.sigep.repositories.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<VehicleModel> findAll() {
        return vehicleRepository.findAll();
    }

    public void register(VehicleRecordDto vehicleDto) {
        var vehicleModel = new VehicleModel(vehicleDto.registrationNumber());
        BeanUtils.copyProperties(vehicleDto, vehicleModel);
        vehicleRepository.save(vehicleModel);
    }

}
