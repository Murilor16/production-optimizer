package com.projedata.optimizer;

import com.projedata.optimizer.entity.Product;
import com.projedata.optimizer.entity.ProductComposition;
import com.projedata.optimizer.entity.RawMaterial;
import com.projedata.optimizer.repository.ProductRepository;
import com.projedata.optimizer.repository.RawMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RawMaterialRepository rawMaterialRepository, ProductRepository productRepository) {
        return args -> {
            // Matérias-Primas
            RawMaterial aco = rawMaterialRepository.save(new RawMaterial(null, "RM001", "Aço", 1000.0, "kg"));
            RawMaterial plastico = rawMaterialRepository.save(new RawMaterial(null, "RM002", "Plástico", 800.0, "kg"));
            RawMaterial aluminio = rawMaterialRepository.save(new RawMaterial(null, "RM003", "Alumínio", 600.0, "kg"));
            RawMaterial borracha = rawMaterialRepository.save(new RawMaterial(null, "RM004", "Borracha", 400.0, "kg"));
            RawMaterial vidro = rawMaterialRepository.save(new RawMaterial(null, "RM005", "Vidro", 500.0, "kg"));
            RawMaterial cobre = rawMaterialRepository.save(new RawMaterial(null, "RM006", "Cobre", 300.0, "kg"));

            // Produto 1: Widget Premium
            Product widgetPremium = new Product();
            widgetPremium.setCode("P001");
            widgetPremium.setName("Widget Premium");
            widgetPremium.setPrice(250.0);
            
            ProductComposition comp1 = new ProductComposition(null, widgetPremium, aco, 30.0);
            ProductComposition comp2 = new ProductComposition(null, widgetPremium, plastico, 15.0);
            ProductComposition comp3 = new ProductComposition(null, widgetPremium, aluminio, 10.0);
            
            widgetPremium.getComposition().add(comp1);
            widgetPremium.getComposition().add(comp2);
            widgetPremium.getComposition().add(comp3);
            productRepository.save(widgetPremium);

            // Produto 2: Widget Standard
            Product widgetStandard = new Product();
            widgetStandard.setCode("P002");
            widgetStandard.setName("Widget Standard");
            widgetStandard.setPrice(150.0);
            
            ProductComposition comp4 = new ProductComposition(null, widgetStandard, aco, 20.0);
            ProductComposition comp5 = new ProductComposition(null, widgetStandard, plastico, 12.0);
            
            widgetStandard.getComposition().add(comp4);
            widgetStandard.getComposition().add(comp5);
            productRepository.save(widgetStandard);

            // Produto 3: Widget Básico
            Product widgetBasico = new Product();
            widgetBasico.setCode("P003");
            widgetBasico.setName("Widget Básico");
            widgetBasico.setPrice(80.0);
            
            ProductComposition comp6 = new ProductComposition(null, widgetBasico, aco, 10.0);
            ProductComposition comp7 = new ProductComposition(null, widgetBasico, borracha, 8.0);
            
            widgetBasico.getComposition().add(comp6);
            widgetBasico.getComposition().add(comp7);
            productRepository.save(widgetBasico);

            // Produto 4: Gadget Deluxe
            Product gadgetDeluxe = new Product();
            gadgetDeluxe.setCode("P004");
            gadgetDeluxe.setName("Gadget Deluxe");
            gadgetDeluxe.setPrice(320.0);
            
            ProductComposition comp8 = new ProductComposition(null, gadgetDeluxe, aluminio, 25.0);
            ProductComposition comp9 = new ProductComposition(null, gadgetDeluxe, vidro, 20.0);
            ProductComposition comp10 = new ProductComposition(null, gadgetDeluxe, cobre, 15.0);
            
            gadgetDeluxe.getComposition().add(comp8);
            gadgetDeluxe.getComposition().add(comp9);
            gadgetDeluxe.getComposition().add(comp10);
            productRepository.save(gadgetDeluxe);

            // Produto 5: Gadget Simples
            Product gadgetSimples = new Product();
            gadgetSimples.setCode("P005");
            gadgetSimples.setName("Gadget Simples");
            gadgetSimples.setPrice(120.0);
            
            ProductComposition comp11 = new ProductComposition(null, gadgetSimples, plastico, 15.0);
            ProductComposition comp12 = new ProductComposition(null, gadgetSimples, borracha, 10.0);
            
            gadgetSimples.getComposition().add(comp11);
            gadgetSimples.getComposition().add(comp12);
            productRepository.save(gadgetSimples);

            System.out.println("✅ Dados de teste carregados com sucesso!");
            System.out.println("📦 6 Matérias-Primas criadas");
            System.out.println("🎯 5 Produtos criados");
        };
    }
}
