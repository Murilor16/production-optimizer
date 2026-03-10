package com.projedata.optimizer.service;

import com.projedata.optimizer.dto.OptimizationResultDTO;
import com.projedata.optimizer.dto.ProductionSuggestionDTO;
import com.projedata.optimizer.entity.Product;
import com.projedata.optimizer.entity.ProductComposition;
import com.projedata.optimizer.entity.RawMaterial;
import com.projedata.optimizer.repository.ProductRepository;
import com.projedata.optimizer.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductionOptimizationService {
    
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductionOptimizationService(ProductRepository productRepository, RawMaterialRepository rawMaterialRepository) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }
    
    @Transactional(readOnly = true)
    public OptimizationResultDTO optimizeProduction() {
        List<Product> products = productRepository.findAll();
        Map<Long, Double> availableStock = new HashMap<>();
        
        for (RawMaterial rm : rawMaterialRepository.findAll()) {
            availableStock.put(rm.getId(), rm.getStockQuantity());
        }
        
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        
        List<ProductionSuggestionDTO> suggestions = new ArrayList<>();
        double totalRevenue = 0.0;
        
        for (Product product : products) {
            int maxQuantity = calculateMaxProducible(product, availableStock);
            
            if (maxQuantity > 0) {
                consumeRawMaterials(product, maxQuantity, availableStock);
                
                double totalValue = product.getPrice() * maxQuantity;
                suggestions.add(new ProductionSuggestionDTO(
                        product.getId(),
                        product.getCode(),
                        product.getName(),
                        product.getPrice(),
                        maxQuantity,
                        totalValue
                ));
                totalRevenue += totalValue;
            }
        }
        
        return new OptimizationResultDTO(suggestions, totalRevenue);
    }
    
    public int calculateMaxProducible(Product product, Map<Long, Double> availableStock) {
        if (product.getComposition().isEmpty()) {
            return 0;
        }
        
        int maxQuantity = Integer.MAX_VALUE;
        
        for (ProductComposition comp : product.getComposition()) {
            Long rawMaterialId = comp.getRawMaterial().getId();
            Double available = availableStock.getOrDefault(rawMaterialId, 0.0);
            Double required = comp.getQuantity();
            
            if (required <= 0) {
                continue;
            }
            
            int possibleQuantity = (int) (available / required);
            maxQuantity = Math.min(maxQuantity, possibleQuantity);
        }
        
        return maxQuantity == Integer.MAX_VALUE ? 0 : maxQuantity;
    }
    
    private void consumeRawMaterials(Product product, int quantity, Map<Long, Double> availableStock) {
        for (ProductComposition comp : product.getComposition()) {
            Long rawMaterialId = comp.getRawMaterial().getId();
            Double currentStock = availableStock.get(rawMaterialId);
            Double consumed = comp.getQuantity() * quantity;
            availableStock.put(rawMaterialId, currentStock - consumed);
        }
    }
}
