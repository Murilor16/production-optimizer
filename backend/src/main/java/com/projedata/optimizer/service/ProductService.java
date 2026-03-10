package com.projedata.optimizer.service;

import com.projedata.optimizer.dto.CompositionItemDTO;
import com.projedata.optimizer.dto.ProductDTO;
import com.projedata.optimizer.entity.Product;
import com.projedata.optimizer.entity.ProductComposition;
import com.projedata.optimizer.repository.ProductRepository;
import com.projedata.optimizer.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductService(ProductRepository productRepository, RawMaterialRepository rawMaterialRepository) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    @Transactional
    public ProductDTO create(ProductDTO dto) {
        if (productRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Código já existe: " + dto.getCode());
        }
        if (dto.getPrice() <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }
        if (dto.getComposition() == null || dto.getComposition().isEmpty()) {
            throw new RuntimeException("Produto deve ter pelo menos uma matéria-prima na composição");
        }
        
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        
        for (CompositionItemDTO item : dto.getComposition()) {
            if (item.getQuantity() <= 0) {
                throw new RuntimeException("Quantidade de matéria-prima deve ser maior que zero");
            }
            ProductComposition composition = new ProductComposition();
            composition.setProduct(product);
            composition.setRawMaterial(rawMaterialRepository.findById(item.getRawMaterialId())
                    .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada")));
            composition.setQuantity(item.getQuantity());
            product.getComposition().add(composition);
        }
        
        return toDTO(productRepository.save(product));
    }
    
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        if (!product.getCode().equals(dto.getCode()) && productRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Código já existe: " + dto.getCode());
        }
        if (dto.getPrice() <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }
        if (dto.getComposition() == null || dto.getComposition().isEmpty()) {
            throw new RuntimeException("Produto deve ter pelo menos uma matéria-prima na composição");
        }
        
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        
        product.getComposition().clear();
        
        for (CompositionItemDTO item : dto.getComposition()) {
            if (item.getQuantity() <= 0) {
                throw new RuntimeException("Quantidade de matéria-prima deve ser maior que zero");
            }
            ProductComposition composition = new ProductComposition();
            composition.setProduct(product);
            composition.setRawMaterial(rawMaterialRepository.findById(item.getRawMaterialId())
                    .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada")));
            composition.setQuantity(item.getQuantity());
            product.getComposition().add(composition);
        }
        
        return toDTO(productRepository.save(product));
    }
    
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    
    private ProductDTO toDTO(Product entity) {
        List<CompositionItemDTO> composition = entity.getComposition().stream()
                .map(c -> new CompositionItemDTO(
                        c.getRawMaterial().getId(),
                        c.getRawMaterial().getCode(),
                        c.getRawMaterial().getName(),
                        c.getQuantity()
                ))
                .collect(Collectors.toList());
        
        return new ProductDTO(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getPrice(),
                composition
        );
    }
}
