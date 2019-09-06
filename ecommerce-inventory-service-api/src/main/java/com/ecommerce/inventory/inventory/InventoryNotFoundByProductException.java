package com.ecommerce.inventory.inventory;

import com.ecommerce.shared.exception.AppException;
import com.google.common.collect.ImmutableMap;

import static com.ecommerce.inventory.InventoryErrorCode.INVENTORY_NOT_FOUND_BY_PRODUCT;

public class InventoryNotFoundByProductException extends AppException {

    public InventoryNotFoundByProductException(String productId) {
        super(INVENTORY_NOT_FOUND_BY_PRODUCT, ImmutableMap.of("productId", productId));
    }

}
