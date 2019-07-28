package com.ecommerce.inventory.inventory;

import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.inventory.inventory.Inventory;
import com.ecommerce.inventory.inventory.InventoryRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InventoryEventHandler {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private InventoryRepository repository;

    public InventoryEventHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createInventory(String productId, String productName) {
        Inventory inventory = Inventory.create(productId, productName);
        repository.save(inventory);
        logger.info("Created inventory for product[{}].", productId);
    }
}
