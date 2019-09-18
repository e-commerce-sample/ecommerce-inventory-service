package com.ecommerce.inventory.sdk.event.inventory;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class InventoryChangedEvent extends InventoryEvent {
    private int remains;

    @ConstructorProperties({"productId", "remains"})
    public InventoryChangedEvent(String productId, int remains) {
        super(productId);
        this.remains = remains;
    }
}
