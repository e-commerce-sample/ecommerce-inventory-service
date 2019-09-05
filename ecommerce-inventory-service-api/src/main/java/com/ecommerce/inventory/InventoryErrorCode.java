package com.ecommerce.inventory;


import com.ecommerce.shared.exception.ErrorCode;

public enum InventoryErrorCode implements ErrorCode {
    INVENTORY_NOT_FOUND(404, "没有找到产品库存项");
    private int status;
    private String message;

    InventoryErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
