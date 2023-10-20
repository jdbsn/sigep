package com.sigep.controllers;

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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/vehicles/register")
    public String registerVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto, BindingResult result) {
        boolean registration = vehicleService.register(vehicleDto);

        if(!registration) {
            ObjectError error = new ObjectError("registrationTaken", "Placa já cadastrada.");
            result.addError(error);
            return "registerVehicle";
        }

        return "redirect:/vehicles";
    }

    @PostMapping("/vehicles/search")
    public String searchVehicle(@ModelAttribute @Valid VehicleRecordDto vehicleDto, Model model) {
        var vehicle = vehicleService.findByRegistration(vehicleDto.registrationNumber());

        if(vehicle == null) {
            model.addAttribute("error", "Veículo não encontrado.");
            return "errorPage";
        }

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

    @GetMapping("/vehicles/search")
    public String showSearchResult(@RequestParam Map<String, String> filters, Model model) {
        var vehicles = vehicleService.findByState(State.valueOf(filters.get("state")));

        List<State> states = Arrays.asList(State.values());

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("states", states);
        model.addAttribute("filters", State.valueOf(filters.get("state")));

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
