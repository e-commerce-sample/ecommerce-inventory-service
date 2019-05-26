package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.DomainEventAwareAggregate;
import com.ecommerce.common.event.inventory.InventoryChangedEvent;

import java.time.Instant;

public class Inventory extends DomainEventAwareAggregate {
    private InventoryId id;
    private String productId;
    private String name;
    private int remains;
    private Instant createdAt;

    private Inventory() {
    }

    private Inventory(InventoryId id, String name) {
        this.id = id;
        this.name = name;
        this.remains = 100;
        this.createdAt = Instant.now();
    }

    public static Inventory create(InventoryId productId, String name) {
        return new Inventory(productId, name);
    }

    public void decrease(int number) {
        this.remains = this.remains - number;
        raiseEvent(new InventoryChangedEvent(productId, remains));
    }

    public InventoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRemains() {
        return remains;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
