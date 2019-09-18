package com.ecommerce.inventory.inventory;

import com.ecommerce.order.sdk.event.order.OrderCreatedEvent;
import com.ecommerce.order.sdk.event.order.OrderItem;
import com.ecommerce.product.sdk.event.product.ProductNameUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class InventoryEventHandler {

    private InventoryRepository repository;

    public InventoryEventHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createInventory(String productId, String productName) {
        Inventory inventory = Inventory.create(productId, productName);
        repository.save(inventory);
        log.info("Created inventory[{}] for product[{}].", inventory.getId(), productId);
    }

    @Transactional
    public void updateProductName(ProductNameUpdatedEvent event) {
        Inventory inventory = repository.byProductId(event.getProductId());
        inventory.updateProductName(event.getNewName());
        repository.save(inventory);
        log.info("Inventory[{}] product[{}] name updated due to product change.", inventory.getId(), event.getProductId());
    }

    @Transactional
    public void decrease(OrderCreatedEvent event) {
        List<OrderItem> orderItems = event.getItems();
        orderItems.forEach(orderItem -> {
            Inventory inventory = repository.byProductId(orderItem.getProductId());
            inventory.decrease(orderItem.getCount());
            repository.save(inventory);
            log.info("Inventory[{}] decreased to {} due to order[{}] creation.",
                    inventory.getId(), inventory.getRemains(), event.getOrderId());
        });
    }
}
