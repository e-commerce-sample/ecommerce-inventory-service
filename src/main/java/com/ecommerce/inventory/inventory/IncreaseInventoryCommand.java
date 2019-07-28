package com.ecommerce.inventory.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;

public class IncreaseInventoryCommand {
    @Min(1)
    private int increaseNumber;

    @JsonCreator
    public IncreaseInventoryCommand(@JsonProperty("increaseNumber") int increaseNumber) {
        this.increaseNumber = increaseNumber;
    }

    public int getIncreaseNumber() {
        return increaseNumber;
    }
}
