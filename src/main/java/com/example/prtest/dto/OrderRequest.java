package com.example.prtest.dto;

import javax.validation.constraints.NotNull;

public class OrderRequest {
    @NotNull
    private String productCode;
    private Integer quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
