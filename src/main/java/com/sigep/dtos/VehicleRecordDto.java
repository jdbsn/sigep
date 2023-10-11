package com.sigep.dtos;

import com.sigep.enums.State;
import jakarta.validation.constraints.NotBlank;

public record VehicleRecordDto(@NotBlank String registrationNumber,  String city, State state,
                               String make, String model, String color, String ownerId) {

}
