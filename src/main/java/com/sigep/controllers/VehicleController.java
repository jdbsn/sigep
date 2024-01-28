package com.sigep.controllers;

import com.sigep.dtos.FilterDTO;
import com.sigep.dtos.VehicleRecordDto;
import com.sigep.enums.State;
import com.sigep.services.VehicleService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

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
    public String listVehicles(@NotNull Model model, VehicleRecordDto vehicleDto) {
        var vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);

        return "listVehicles";
    }

    @GetMapping("/vehicles/register")
    public String showRegisterVehicle(VehicleRecordDto vehicleDto) {
        return "registerVehicle";
    }

    @PostMapping("/vehicles/search")
    public String registerVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto, BindingResult resultModel, Model model) {
        var vehicle = vehicleService.findByRegistration(vehicleDto.registrationNumber());

        if(vehicle == null) {
            model.addAttribute("error", "Veículo não encontrado.");
            return "errorPage";
        }

        return "redirect:/vehicles/" + vehicle.getIdVehicle();
    }

    @PostMapping("/vehicles/register")
    public String searchVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto, Model model, BindingResult result) {
        boolean registration = vehicleService.register(vehicleDto);

        if(!registration) {
            ObjectError error = new ObjectError("registrationTaken", "Placa já cadastrada.");
            result.addError(error);
            return "registerVehicle";
        }

        return "redirect:/vehicles";
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

    @GetMapping("/vehicles/search")
    public String showSearchResult(FilterDTO filters, Model model) {
        var vehicles = vehicleService.findWithFilters(filters);

        model.addAttribute("vehicles", vehicles);

        if(filters.state() != null) {
            model.addAttribute("state", State.valueOf(filters.state()));
        }
        model.addAttribute("make", filters.make());
        model.addAttribute("year", filters.year());

        return "listVehicles";
    }

    @PostMapping("/vehicles/{id}/delete")
    public String deleteVehicle(@PathVariable(value = "id") UUID id, Model model) {
        if(!vehicleService.deleteById(id)) {
            model.addAttribute("error", "Veículo não encontrado.");
            return "errorPage";
        }

        return "redirect:/vehicles";
    }

}
