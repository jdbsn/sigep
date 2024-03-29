package com.sigep.services;

import com.sigep.dtos.FilterDTO;
import com.sigep.dtos.VehicleRecordDto;
import com.sigep.enums.State;
import com.sigep.models.VehicleModel;
import com.sigep.repositories.VehicleRepository;
import com.sigep.specifications.VehicleSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public boolean register(VehicleRecordDto vehicleDto) {
        VehicleModel vehicle = findByRegistration(vehicleDto.registrationNumber());

        if(vehicle != null) {
            return false;
        }

        var vehicleModel = new VehicleModel(vehicleDto.registrationNumber().toUpperCase());
        BeanUtils.copyProperties(vehicleDto, vehicleModel);
        vehicleRepository.save(vehicleModel);
        return true;
    }

    public VehicleModel findById(UUID id) {
        Optional<VehicleModel> vehicle = vehicleRepository.findById(id);

        if(vehicle.isEmpty()) {
            return null;
        }
        return vehicle.get();
    }

    public VehicleModel findByRegistration(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber.toUpperCase());
    }

    public List<VehicleModel> findByState(State state) {
        return vehicleRepository.findByState(state);
    }

    public boolean deleteById(UUID id) {
        VehicleModel vehicle = findById(id);

        if(vehicle == null) {
            return false;
        }

        vehicleRepository.deleteById(id);
        return true;
    }

    public List<VehicleModel> findWithFilters(FilterDTO filter) {
        return vehicleRepository.findAll(Specification
                .where(VehicleSpecification.hasState(filter.state()))
                .and(VehicleSpecification.hasMake(filter.make()))
                .and(VehicleSpecification.hasYear(filter.year())));
    }

}
