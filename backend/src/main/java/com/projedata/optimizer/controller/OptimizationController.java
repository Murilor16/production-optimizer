package com.projedata.optimizer.controller;

import com.projedata.optimizer.dto.OptimizationResultDTO;
import com.projedata.optimizer.service.ProductionOptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimization")
@CrossOrigin(origins = "*")
public class OptimizationController {
    
    private final ProductionOptimizationService service;

    public OptimizationController(ProductionOptimizationService service) {
        this.service = service;
    }
    
    @GetMapping("/calculate")
    public ResponseEntity<OptimizationResultDTO> calculateOptimalProduction() {
        return ResponseEntity.ok(service.optimizeProduction());
    }
}
