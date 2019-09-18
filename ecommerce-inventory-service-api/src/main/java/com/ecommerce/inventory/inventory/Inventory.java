package com.ecommerce.inventory.inventory;

import com.ecommerce.inventory.sdk.event.inventory.InventoryChangedEvent;
import com.ecommerce.inventory.sdk.representation.inventory.InventoryRepresentation;
import com.ecommerce.shared.model.BaseAggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

import static com.ecommerce.shared.utils.UuidGenerator.newUuid;

@Getter
@Builder
public class Inventory extends BaseAggregate {
    private String id;
    private String productId;
    private String productName;
    private int remains;
    private Instant createdAt;

    public static Inventory create(String productId, String productName) {
        return Inventory.builder()
                .id(newUuid())
                .productId(productId)
                .productName(productName)
                .remains(0)
                .createdAt(Instant.now())
                .build();
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

    public InventoryRepresentation toRepresentation() {
        return new InventoryRepresentation(this.id,
                this.productName,
                this.remains);
    }

}
