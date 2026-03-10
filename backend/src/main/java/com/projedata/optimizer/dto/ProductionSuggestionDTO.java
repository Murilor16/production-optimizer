package com.projedata.optimizer.dto;

public class ProductionSuggestionDTO {
    private Long productId;
    private String productCode;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalValue;

    public ProductionSuggestionDTO() {}

    public ProductionSuggestionDTO(Long productId, String productCode, String productName, Double productPrice, Integer quantity, Double totalValue) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Double getProductPrice() { return productPrice; }
    public void setProductPrice(Double productPrice) { this.productPrice = productPrice; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getTotalValue() { return totalValue; }
    public void setTotalValue(Double totalValue) { this.totalValue = totalValue; }
}
