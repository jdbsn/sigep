package com.sigep.repositories;

import com.sigep.enums.State;
import com.sigep.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleModel, UUID> {

    VehicleModel findByRegistrationNumber(String registrationNumber);

    List<VehicleModel> findByState(State state);

}
