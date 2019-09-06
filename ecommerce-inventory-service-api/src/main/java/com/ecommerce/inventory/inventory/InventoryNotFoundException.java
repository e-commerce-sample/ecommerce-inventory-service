package com.ecommerce.inventory.inventory;

import com.ecommerce.shared.exception.AppException;
import com.google.common.collect.ImmutableMap;

import static com.ecommerce.inventory.InventoryErrorCode.INVENTORY_NOT_FOUND;

public class InventoryNotFoundException extends AppException {
    public InventoryNotFoundException(String id) {
        super(INVENTORY_NOT_FOUND, ImmutableMap.of("invetoryId", id));
    }


}
