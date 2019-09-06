package com.ecommerce.inventory.inventory;

import com.ecommerce.shared.event.DomainEventRecorder;
import com.ecommerce.shared.jackson.DefaultObjectMapper;
import com.ecommerce.shared.model.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class InventoryRepository extends BaseRepository<Inventory> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public InventoryRepository(NamedParameterJdbcTemplate jdbcTemplate,
                               DefaultObjectMapper objectMapper,
                               DomainEventRecorder eventRecorder) {
        super(eventRecorder);
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


    public Inventory byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM INVENTORY WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new InventoryNotFoundException(id);
        }
    }

    private RowMapper<Inventory> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Inventory.class);
    }

    public Inventory byProductId(String productId) {
        try {
            String sql = "SELECT JSON_CONTENT FROM INVENTORY WHERE PRODUCT_ID=:productId;";
            return jdbcTemplate.queryForObject(sql, of("productId", productId), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new InventoryNotFoundByProductException(productId);
        }
    }
}
