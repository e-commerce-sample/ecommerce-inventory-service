package com.ecommerce.inventory;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.order.OrderCreatedEvent;
import com.ecommerce.common.event.product.ProductCreatedEvent;
import com.ecommerce.common.event.product.ProductNameUpdatedEvent;
import com.ecommerce.inventory.inventory.InventoryEventHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class InventoryRabbitListener {
    private InventoryEventHandler eventHandler;

    public InventoryRabbitListener(InventoryEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @RabbitHandler
    public void on(ProductCreatedEvent event) {
        eventHandler.createInventory(event.getProductId(), event.getName());
    }

    @RabbitHandler
    public void on(ProductNameUpdatedEvent event) {
        eventHandler.updateProductName(event);
    }

    @RabbitHandler
    public void on(OrderCreatedEvent event) {
        eventHandler.decrease(event);
    }

}
