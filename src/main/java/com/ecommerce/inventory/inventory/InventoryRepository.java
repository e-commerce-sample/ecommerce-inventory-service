package com.ecommerce.inventory.inventory;

import com.ecommerce.common.event.DomainEventAwareRepository;
import com.ecommerce.common.utils.DefaultObjectMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class InventoryRepository extends DomainEventAwareRepository<Inventory> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public InventoryRepository(NamedParameterJdbcTemplate jdbcTemplate,
                               DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doSave(Inventory inventory) {
        String sql = "INSERT INTO INVENTORY (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", inventory.getId().toString(), "json", objectMapper.writeValueAsString(inventory));
        jdbcTemplate.update(sql, paramMap);
    }
}
