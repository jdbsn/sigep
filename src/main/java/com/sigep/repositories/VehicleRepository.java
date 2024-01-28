package com.sigep.repositories;

import com.sigep.enums.State;
import com.sigep.models.VehicleModel;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleModel, UUID>, JpaSpecificationExecutor<VehicleModel> {

    VehicleModel findByRegistrationNumber(String registrationNumber);

    List<VehicleModel> findByState(State state);

    List<VehicleModel> findAll(@Nullable Specification<VehicleModel> spec);

}
