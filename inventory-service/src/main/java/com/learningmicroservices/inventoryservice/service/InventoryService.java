package com.learningmicroservices.inventoryservice.service;


import com.learningmicroservices.inventoryservice.dto.InventoryResponse;
import com.learningmicroservices.inventoryservice.model.Inventory;
import com.learningmicroservices.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> listSkuCode){
        return inventoryRepository.findBySkuCodeIn(listSkuCode).stream()
                .map(inventory -> {
                    InventoryResponse inventoryResponse = new InventoryResponse();
                    inventoryResponse.setSkuCode(inventory.getSkuCode());
                    inventoryResponse.setInStock(inventory.getQuantity() > 0 ? true : false);
                    return inventoryResponse;
                }).collect(toList());
    }
}
