package com.projedata.optimizer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String code;
    
    @NotBlank
    private String name;
    
    @NotNull
    private Double price;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductComposition> composition = new ArrayList<>();

    public Product() {}

    public Product(Long id, String code, String name, Double price, List<ProductComposition> composition) {
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
    public List<ProductComposition> getComposition() { return composition; }
    public void setComposition(List<ProductComposition> composition) { this.composition = composition; }
}
