package com.projedata.optimizer.dto;

public class RawMaterialDTO {
    private Long id;
    private String code;
    private String name;
    private Double stockQuantity;
    private String unit;

    public RawMaterialDTO() {}

    public RawMaterialDTO(Long id, String code, String name, Double stockQuantity, String unit) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Double stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
