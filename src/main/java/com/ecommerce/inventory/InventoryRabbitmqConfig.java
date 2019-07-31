package com.ecommerce.inventory;

import com.ecommerce.common.event.EcommerceRabbitProperties;
import com.google.common.collect.ImmutableMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ecommerce.common.event.DomainEventType.ORDER_CREATED;
import static com.ecommerce.common.event.DomainEventType.PRODUCT_CREATED;
import static com.ecommerce.common.event.DomainEventType.PRODUCT_NAME_UPDATED;
import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class InventoryRabbitmqConfig {

    private EcommerceRabbitProperties properties;

    public InventoryRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }

    //Product服务的"发送方Exchange"，通常不应该在消费方配置发送方的Exchange，这里只是作demo用
    @Bean
    public TopicExchange productPublishExchange() {
        return new TopicExchange("product-publish-x", true, false, ImmutableMap.of("alternate-exchange", "product-publish-dlx"));
    }


    //接收order上下文的OrderCreatedEvent，用于扣减库存量
    @Bean
    public Binding bindToOrderCreated() {
        return new Binding(properties.getReceiveQ(), QUEUE, "order-publish-x", ORDER_CREATED.toRoutingKey(), null);
    }

    //接收product上下文的所有ProductCreatedEvent
    @Bean
    public Binding bindToProductCreated() {
        return new Binding(properties.getReceiveQ(), QUEUE, "product-publish-x", PRODUCT_CREATED.toRoutingKey(), null);
    }

    //接收product上下文的所有ProductNameUpdatedEvent
    @Bean
    public Binding bindToProductNameUpdated() {
        return new Binding(properties.getReceiveQ(), QUEUE, "product-publish-x", PRODUCT_NAME_UPDATED.toRoutingKey(), null);
    }


}
