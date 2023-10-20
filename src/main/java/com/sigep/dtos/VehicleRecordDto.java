package com.sigep.dtos;

import com.sigep.enums.State;
import jakarta.validation.constraints.NotBlank;

public record VehicleRecordDto(@NotBlank String registrationNumber, @NotBlank String city, State state,
                               @NotBlank String make, @NotBlank String model, @NotBlank String color,
                               @NotBlank String ownerId, Integer year) {

}
