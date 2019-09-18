package com.ecommerce.inventory.sdk.command.inventory;

import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class IncreaseInventoryCommand {
    @Min(1)
    private int increaseNumber;
}
