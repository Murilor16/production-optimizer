package com.projedata.optimizer.dto;

import java.util.List;

public class OptimizationResultDTO {
    private List<ProductionSuggestionDTO> suggestions;
    private Double totalRevenue;

    public OptimizationResultDTO() {}

    public OptimizationResultDTO(List<ProductionSuggestionDTO> suggestions, Double totalRevenue) {
        this.suggestions = suggestions;
        this.totalRevenue = totalRevenue;
    }

    public List<ProductionSuggestionDTO> getSuggestions() { return suggestions; }
    public void setSuggestions(List<ProductionSuggestionDTO> suggestions) { this.suggestions = suggestions; }
    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
}
