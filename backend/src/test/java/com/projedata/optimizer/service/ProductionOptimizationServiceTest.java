package com.projedata.optimizer.service;

import com.projedata.optimizer.dto.OptimizationResultDTO;
import com.projedata.optimizer.dto.ProductionSuggestionDTO;
import com.projedata.optimizer.entity.Product;
import com.projedata.optimizer.entity.ProductComposition;
import com.projedata.optimizer.entity.RawMaterial;
import com.projedata.optimizer.repository.ProductRepository;
import com.projedata.optimizer.repository.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductionOptimizationServiceTest {
    
    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private RawMaterialRepository rawMaterialRepository;
    
    @InjectMocks
    private ProductionOptimizationService service;
    
    private RawMaterial rawMaterial1;
    private RawMaterial rawMaterial2;
    private Product product1;
    private Product product2;
    
    @BeforeEach
    void setUp() {
        rawMaterial1 = new RawMaterial(1L, "RM001", "Steel", 1000.0, "kg");
        rawMaterial2 = new RawMaterial(2L, "RM002", "Plastic", 500.0, "kg");
        
        product1 = new Product(1L, "P001", "Product A", 100.0, new ArrayList<>());
        ProductComposition comp1 = new ProductComposition(1L, product1, rawMaterial1, 10.0);
        ProductComposition comp2 = new ProductComposition(2L, product1, rawMaterial2, 5.0);
        product1.getComposition().add(comp1);
        product1.getComposition().add(comp2);
        
        product2 = new Product(2L, "P002", "Product B", 50.0, new ArrayList<>());
        ProductComposition comp3 = new ProductComposition(3L, product2, rawMaterial1, 5.0);
        product2.getComposition().add(comp3);
    }
    
    @Test
    void testOptimizeProduction_shouldPrioritizeHigherPriceProduct() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        when(rawMaterialRepository.findAll()).thenReturn(Arrays.asList(rawMaterial1, rawMaterial2));
        
        OptimizationResultDTO result = service.optimizeProduction();
        
        assertNotNull(result);
        assertFalse(result.getSuggestions().isEmpty());
        assertTrue(result.getTotalRevenue() > 0);
        
        ProductionSuggestionDTO firstSuggestion = result.getSuggestions().get(0);
        assertEquals("P001", firstSuggestion.getProductCode());
    }
    
    @Test
    void testCalculateMaxProducible_withSufficientStock() {
        Map<Long, Double> availableStock = new HashMap<>();
        availableStock.put(1L, 1000.0);
        availableStock.put(2L, 500.0);
        
        int maxQuantity = service.calculateMaxProducible(product1, availableStock);
        
        assertEquals(100, maxQuantity);
    }
    
    @Test
    void testCalculateMaxProducible_withLimitedStock() {
        Map<Long, Double> availableStock = new HashMap<>();
        availableStock.put(1L, 50.0);
        availableStock.put(2L, 500.0);
        
        int maxQuantity = service.calculateMaxProducible(product1, availableStock);
        
        assertEquals(5, maxQuantity);
    }
    
    @Test
    void testCalculateMaxProducible_withNoStock() {
        Map<Long, Double> availableStock = new HashMap<>();
        availableStock.put(1L, 0.0);
        availableStock.put(2L, 0.0);
        
        int maxQuantity = service.calculateMaxProducible(product1, availableStock);
        
        assertEquals(0, maxQuantity);
    }
    
    @Test
    void testOptimizeProduction_withMultipleProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        when(rawMaterialRepository.findAll()).thenReturn(Arrays.asList(rawMaterial1, rawMaterial2));
        
        OptimizationResultDTO result = service.optimizeProduction();
        
        assertNotNull(result);
        assertTrue(result.getSuggestions().size() <= 2);
        
        double calculatedRevenue = result.getSuggestions().stream()
                .mapToDouble(ProductionSuggestionDTO::getTotalValue)
                .sum();
        assertEquals(calculatedRevenue, result.getTotalRevenue(), 0.01);
    }
}
