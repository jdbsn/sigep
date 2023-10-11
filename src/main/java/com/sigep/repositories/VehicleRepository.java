package com.sigep.repositories;

import com.sigep.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleModel, UUID> {
}
