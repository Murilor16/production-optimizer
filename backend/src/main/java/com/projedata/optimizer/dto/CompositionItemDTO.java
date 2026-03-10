package com.projedata.optimizer.dto;

public class CompositionItemDTO {
    private Long rawMaterialId;
    private String rawMaterialCode;
    private String rawMaterialName;
    private Double quantity;

    public CompositionItemDTO() {}

    public CompositionItemDTO(Long rawMaterialId, String rawMaterialCode, String rawMaterialName, Double quantity) {
        this.rawMaterialId = rawMaterialId;
        this.rawMaterialCode = rawMaterialCode;
        this.rawMaterialName = rawMaterialName;
        this.quantity = quantity;
    }

    public Long getRawMaterialId() { return rawMaterialId; }
    public void setRawMaterialId(Long rawMaterialId) { this.rawMaterialId = rawMaterialId; }
    public String getRawMaterialCode() { return rawMaterialCode; }
    public void setRawMaterialCode(String rawMaterialCode) { this.rawMaterialCode = rawMaterialCode; }
    public String getRawMaterialName() { return rawMaterialName; }
    public void setRawMaterialName(String rawMaterialName) { this.rawMaterialName = rawMaterialName; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
}
