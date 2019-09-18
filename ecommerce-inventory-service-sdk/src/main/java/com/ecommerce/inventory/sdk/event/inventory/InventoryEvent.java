package com.ecommerce.inventory.sdk.event.inventory;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public abstract class InventoryEvent extends DomainEvent {
    private String productId;

    protected InventoryEvent(String productId) {
        this.productId = productId;
    }

}
