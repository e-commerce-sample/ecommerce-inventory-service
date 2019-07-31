package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.order.OrderCreatedEvent;
import com.ecommerce.common.event.order.OrderItem;
import com.ecommerce.common.event.product.ProductNameUpdatedEvent;
import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        logger.info("Created inventory[{}] for product[{}].", inventory.getId(), productId);
    }

    @Transactional
    public void updateProductName(ProductNameUpdatedEvent event) {
        Inventory inventory = repository.byProductId(event.getProductId());
        inventory.updateProductName(event.getNewName());
        repository.save(inventory);
        logger.info("Inventory[{}] product[{}] name updated due to product change.", inventory.getId(), event.getProductId());
    }

    @Transactional
    public void decrease(OrderCreatedEvent event) {
        List<OrderItem> orderItems = event.getItems();
        orderItems.forEach(orderItem -> {
            Inventory inventory = repository.byProductId(orderItem.getProductId());
            inventory.decrease(orderItem.getCount());
            repository.save(inventory);
            logger.info("Inventory[{}] decreased to {} due to order[{}] creation.",
                    inventory.getId(), inventory.getRemains(), event.getOrderId());
        });
    }
}
