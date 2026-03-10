package com.projedata.optimizer.service;

import com.projedata.optimizer.dto.RawMaterialDTO;
import com.projedata.optimizer.entity.RawMaterial;
import com.projedata.optimizer.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawMaterialService {
    
    private final RawMaterialRepository repository;

    public RawMaterialService(RawMaterialRepository repository) {
        this.repository = repository;
    }
    
    @Transactional(readOnly = true)
    public List<RawMaterialDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public RawMaterialDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));
    }
    
    @Transactional
    public RawMaterialDTO create(RawMaterialDTO dto) {
        if (repository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Código já existe: " + dto.getCode());
        }
        if (dto.getStockQuantity() < 0) {
            throw new RuntimeException("Quantidade em estoque não pode ser negativa");
        }
        RawMaterial entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }
    
    @Transactional
    public RawMaterialDTO update(Long id, RawMaterialDTO dto) {
        RawMaterial entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));
        
        if (!entity.getCode().equals(dto.getCode()) && repository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Código já existe: " + dto.getCode());
        }
        if (dto.getStockQuantity() < 0) {
            throw new RuntimeException("Quantidade em estoque não pode ser negativa");
        }
        
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStockQuantity(dto.getStockQuantity());
        entity.setUnit(dto.getUnit());
        return toDTO(repository.save(entity));
    }
    
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matéria-prima não encontrada");
        }
        repository.deleteById(id);
    }
    
    private RawMaterialDTO toDTO(RawMaterial entity) {
        return new RawMaterialDTO(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getStockQuantity(),
                entity.getUnit()
        );
    }
    
    private RawMaterial toEntity(RawMaterialDTO dto) {
        return new RawMaterial(
                dto.getId(),
                dto.getCode(),
                dto.getName(),
                dto.getStockQuantity(),
                dto.getUnit()
        );
    }
}
