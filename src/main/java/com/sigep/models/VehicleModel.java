package com.sigep.models;

import com.sigep.enums.State;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
public class VehicleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVehicle;
    @Setter(AccessLevel.NONE)
    private String registrationNumber;
    private String city;
    private State state;
    private String make;
    private String model;
    private String color;
    private String ownerId;
    private int ano;

    public VehicleModel() {

    }

    public VehicleModel(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
