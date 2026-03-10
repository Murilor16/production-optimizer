package com.projedata.optimizer.dto;

import java.util.List;

public class ProductDTO {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private List<CompositionItemDTO> composition;

    public ProductDTO() {}

    public ProductDTO(Long id, String code, String name, Double price, List<CompositionItemDTO> composition) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.composition = composition;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public List<CompositionItemDTO> getComposition() { return composition; }
    public void setComposition(List<CompositionItemDTO> composition) { this.composition = composition; }
}
