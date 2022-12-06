package com.learningmicroservices.inventoryservice.controller;


import com.learningmicroservices.inventoryservice.dto.InventoryResponse;
import com.learningmicroservices.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    // uri= "/api/inventory?skuCode=iphone12&skuCode=iphone14"
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        List<String> listSkuCode1 = skuCode;
        return inventoryService.isInStock(skuCode);
    }
}
