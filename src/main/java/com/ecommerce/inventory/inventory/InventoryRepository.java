package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.DomainEventAwareRepository;
import org.springframework.stereotype.Component;

@Component
public class InventoryRepository extends DomainEventAwareRepository<Inventory> {
    @Override
    protected void doSave(Inventory aggregate) {

    }
}
