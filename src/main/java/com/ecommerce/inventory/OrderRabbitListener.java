package com.ecommerce.inventory;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.order.OrderCreatedEvent;
import com.ecommerce.common.event.product.ProductCreatedEvent;
import com.ecommerce.common.event.product.ProductNameUpdatedEvent;
import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class OrderRabbitListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();


    @RabbitHandler
    public void on(ProductCreatedEvent event) {
    }

    @RabbitHandler
    public void on(ProductNameUpdatedEvent event) {
    }

    @RabbitHandler
    public void on(OrderCreatedEvent event) {
    }


}
