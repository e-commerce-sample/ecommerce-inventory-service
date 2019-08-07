package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.DomainEventAwareAggregate;
import com.ecommerce.common.event.inventory.InventoryChangedEvent;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Inventory extends DomainEventAwareAggregate {
    private InventoryId id;
    private String productId;
    private String productName;
    private int remains;
    private Instant createdAt;

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

    public void updateProductName(String newName) {
        this.productName = newName;
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
