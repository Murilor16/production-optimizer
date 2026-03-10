package com.projedata.optimizer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product_composition")
public class ProductComposition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    @NotNull
    private RawMaterial rawMaterial;
    
    @NotNull
    private Double quantity;

    public ProductComposition() {}

    public ProductComposition(Long id, Product product, RawMaterial rawMaterial, Double quantity) {
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public RawMaterial getRawMaterial() { return rawMaterial; }
    public void setRawMaterial(RawMaterial rawMaterial) { this.rawMaterial = rawMaterial; }
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
}
