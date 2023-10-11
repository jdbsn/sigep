package com.sigep.controllers;

import com.sigep.dtos.VehicleRecordDto;
import com.sigep.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService = new VehicleService();

    @GetMapping("/")
    public String showIndex(VehicleRecordDto vehicleDto) {
        return "index";
    }

    @GetMapping("/vehicles")
    public String listVehicles(Model model, VehicleRecordDto vehicleDto) {
        var vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);

        return "listVehicles";
    }

    @GetMapping("/vehicles/register")
    public String showRegisterVehicle(VehicleRecordDto vehicleDto) {
        return "registerVehicle";
    }

    @PostMapping("/vehicles/register")
    public String registerVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto) {
        vehicleService.register(vehicleDto);

        return "redirect:/vehicles";
    }

    @PostMapping("/vehicles/search")
    public String searchVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto) {
        var vehicle = vehicleService.findByRegistration(vehicleDto.registrationNumber());

        return "redirect:/vehicles/" + vehicle.getIdVehicle();
    }

    @GetMapping("/vehicles/{id}")
    public String showDetailVehicle(@PathVariable(value = "id") UUID id, Model model) {
        var vehicle = vehicleService.findById(id);

        if(vehicle == null) {
            model.addAttribute("error", "Veículo não encontrado.");
            return "errorPage";
        }
        model.addAttribute("vehicle", vehicle);
        return "detailVehicle";
    }

}
