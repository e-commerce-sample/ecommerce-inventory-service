package com.ecommerce.inventory.inventory;

import com.ecommerce.inventory.command.inventory.IncreaseInventoryCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/inventories")
public class InventoryController {
    private final InventoryApplicationService inventoryApplicationService;

    public InventoryController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @PostMapping("/{id}/increase")
    public InventoryId increaseInventory(@PathVariable("id") String inventoryId, @RequestBody @Valid IncreaseInventoryCommand command) {
        return inventoryApplicationService.increase(inventoryId, command);
    }


}
