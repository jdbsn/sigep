package com.sigep.controllers;

import com.sigep.dtos.VehicleRecordDto;
import com.sigep.models.VehicleModel;
import com.sigep.repositories.VehicleRepository;
import com.sigep.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService = new VehicleService();

    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
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

}
