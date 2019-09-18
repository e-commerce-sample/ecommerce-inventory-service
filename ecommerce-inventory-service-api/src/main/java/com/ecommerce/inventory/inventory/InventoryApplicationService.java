package com.ecommerce.inventory.inventory;

import com.ecommerce.inventory.sdk.command.inventory.IncreaseInventoryCommand;
import com.ecommerce.inventory.sdk.representation.inventory.InventoryRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class InventoryApplicationService {

    private InventoryRepository repository;

    public InventoryApplicationService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String increase(String inventoryId, IncreaseInventoryCommand command) {
        Inventory inventory = repository.byId(inventoryId);
        inventory.increase(command.getIncreaseNumber());
        repository.save(inventory);
        log.info("Increased inventory[{}] by {}.", inventoryId, command.getIncreaseNumber());
        return inventory.getId();
    }

    public InventoryRepresentation byId(String inventoryId) {
        return repository
                .byId(inventoryId)
                .toRepresentation();
    }
}
