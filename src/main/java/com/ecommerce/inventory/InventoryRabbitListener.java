package com.ecommerce.inventory;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.order.OrderCreatedEvent;
import com.ecommerce.common.event.product.ProductCreatedEvent;
import com.ecommerce.common.event.product.ProductNameUpdatedEvent;
import com.ecommerce.inventory.inventory.InventoryEventHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class InventoryRabbitListener {
    private InventoryEventHandler eventHandler;

    public InventoryRabbitListener(InventoryEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @NewSpan("Create inventory based on product creation")
    @RabbitHandler
    public void on(ProductCreatedEvent event) {
        eventHandler.createInventory(event.getProductId(), event.getName());
    }

    @NewSpan("Update product name according to product")
    @RabbitHandler
    public void on(ProductNameUpdatedEvent event) {
        eventHandler.updateProductName(event);
    }

    @NewSpan("Decrease inventory on order creation")
    @RabbitHandler
    public void on(OrderCreatedEvent event) {
        eventHandler.decrease(event);
    }

}
