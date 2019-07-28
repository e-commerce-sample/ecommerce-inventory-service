package com.ecommerce.inventory.inventory;

import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InventoryApplicationService {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private InventoryRepository repository;

    public InventoryApplicationService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InventoryId increase(String inventoryId, IncreaseInventoryCommand command) {
        Inventory inventory = repository.byId(InventoryId.of(inventoryId));
        inventory.increase(command.getIncreaseNumber());
        repository.save(inventory);
        logger.info("Increased inventory[{}] by {}.", inventoryId, command.getIncreaseNumber());
        return inventory.getId();
    }
}
