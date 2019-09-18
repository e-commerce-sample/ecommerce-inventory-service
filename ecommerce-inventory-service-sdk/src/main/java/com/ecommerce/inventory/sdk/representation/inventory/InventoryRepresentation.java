package com.ecommerce.inventory.sdk.representation.inventory;

import lombok.Value;

@Value
public class InventoryRepresentation {
    private String id;
    private String productName;
    private int remains;
}
