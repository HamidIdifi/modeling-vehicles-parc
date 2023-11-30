package com.parkingvehicleds.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// VehiculeController.java
@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    private int capaciteParking = 10;
    private int vehiculesLoues = 0;
    private int vehiculesRestitues = 0;

    @GetMapping("/parking")
    public ResponseEntity<Map<String, Integer>> getParkingStatus() {
        int disponibilite = capaciteParking - (vehiculesLoues - vehiculesRestitues);
        Map<String, Integer> parkingStatus = new HashMap<>();
        parkingStatus.put("disponibilite", disponibilite);
        parkingStatus.put("capacite", capaciteParking);
        return ResponseEntity.ok(parkingStatus);
    }

    @PostMapping("/location")
    public ResponseEntity<String> louerVehicule() {
        if (vehiculesLoues < capaciteParking) {
            vehiculesLoues++;
            return ResponseEntity.ok("Véhicule loué avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun véhicule disponible");
        }
    }

    @PostMapping("/restitution")
    public ResponseEntity<String> restituerVehicule() {
        if (vehiculesLoues > 0) {
            vehiculesLoues--;
            vehiculesRestitues++;
            return ResponseEntity.ok("Véhicule restitué avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun véhicule à restituer");
        }
    }
}


