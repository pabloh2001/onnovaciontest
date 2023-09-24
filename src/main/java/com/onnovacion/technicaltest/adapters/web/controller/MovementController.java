package com.onnovacion.technicaltest.adapters.web.controller;

import com.onnovacion.technicaltest.application.service.MovementService;
import com.onnovacion.technicaltest.domain.model.Movement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movements")
public class MovementController {
    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    public ResponseEntity saveMovement(@RequestBody Movement movement) {
        boolean result = this.movementService.save(movement);
        return result ? ResponseEntity.noContent().build():ResponseEntity.badRequest().build();
    }
}
