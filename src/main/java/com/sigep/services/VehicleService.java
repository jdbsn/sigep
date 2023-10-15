package com.sigep.services;

import com.sigep.dtos.VehicleRecordDto;
import com.sigep.enums.State;
import com.sigep.models.VehicleModel;
import com.sigep.repositories.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<VehicleModel> findAll() {
        return vehicleRepository.findAll();
    }

    public void register(VehicleRecordDto vehicleDto) {
        var vehicleModel = new VehicleModel(vehicleDto.registrationNumber().toUpperCase());
        BeanUtils.copyProperties(vehicleDto, vehicleModel);
        vehicleRepository.save(vehicleModel);
    }

    public VehicleModel findById(UUID id) {
        Optional<VehicleModel> vehicle = vehicleRepository.findById(id);

        if(vehicle.isEmpty()) {
            return null;
        }
        return vehicle.get();
    }

    public VehicleModel findByRegistration(String registrationNumber) {
        VehicleModel vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber.toUpperCase());

        return vehicle;
    }

    public List<VehicleModel> findByRegistration(State state) {
        List<VehicleModel> vehicles = vehicleRepository.findByState(state);

        return vehicles;
    }

}
