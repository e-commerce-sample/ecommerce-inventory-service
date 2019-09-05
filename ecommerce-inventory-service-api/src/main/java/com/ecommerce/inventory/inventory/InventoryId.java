package com.ecommerce.inventory.inventory;

import com.ecommerce.shared.model.AbstractId;
import com.ecommerce.shared.utils.UuidGenerator;

public class InventoryId extends AbstractId {
    private InventoryId() {
    }

    private InventoryId(String id) {
        super(id);
    }

    public static InventoryId of(String id) {
        return new InventoryId(id);
    }

    public static InventoryId newId() {
        return of(UuidGenerator.newUuid());
    }
}
