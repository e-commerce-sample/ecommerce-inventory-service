package com.ecommerce.inventory.inventory;

import com.ecommerce.inventory.command.inventory.IncreaseInventoryCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@RestController
@RequestMapping(value = "/inventories")
public class InventoryController {
    private final InventoryApplicationService inventoryApplicationService;

    public InventoryController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @PostMapping("/{id}/increase")
    public Map<String, String> increaseInventory(@PathVariable("id") String inventoryId, @RequestBody @Valid IncreaseInventoryCommand command) {
        return of("id", inventoryApplicationService.increase(inventoryId, command));
    }


}
