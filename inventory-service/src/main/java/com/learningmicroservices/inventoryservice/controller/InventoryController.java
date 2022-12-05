package com.learningmicroservices.inventoryservice.controller;


import com.learningmicroservices.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("id") Long idToFind){
        return inventoryService.isInStock(idToFind);
    }
}
