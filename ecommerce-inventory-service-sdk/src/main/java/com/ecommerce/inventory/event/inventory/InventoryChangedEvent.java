package com.ecommerce.inventory.event.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class InventoryChangedEvent extends InventoryEvent {
    private int remains;

    @JsonCreator
    public InventoryChangedEvent(@JsonProperty("productId") String productId,
                                 @JsonProperty("remains") int remains) {
        super(productId);
        this.remains = remains;
    }
}
