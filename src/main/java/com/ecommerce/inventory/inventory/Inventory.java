package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.DomainEventAwareAggregate;
import com.ecommerce.common.event.inventory.InventoryChangedEvent;

import java.time.Instant;

public class Inventory extends DomainEventAwareAggregate {
    private InventoryId id;
    private String productId;
    private String productName;
    private int remains;
    private Instant createdAt;

    private Inventory() {
    }

    private Inventory(String productId, String productName) {
        this.id = InventoryId.newId();
        this.productId = productId;
        this.productName = productName;
        this.remains = 0;
        this.createdAt = Instant.now();
    }

    public static Inventory create(String productId, String name) {
        return new Inventory(productId, name);
    }

    public void decrease(int number) {
        this.remains = this.remains - number;
        raiseEvent(new InventoryChangedEvent(productId, remains));
    }

    public void increase(int number) {
        this.remains = this.remains + number;
        raiseEvent(new InventoryChangedEvent(productId, remains));
    }

    public InventoryId getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getRemains() {
        return remains;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
