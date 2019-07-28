package com.ecommerce.inventory.inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InventoryTest {

    @Test
    public void should_create_inventory() {
        assertNotNull(Inventory.create("12345", "name"));
    }

}