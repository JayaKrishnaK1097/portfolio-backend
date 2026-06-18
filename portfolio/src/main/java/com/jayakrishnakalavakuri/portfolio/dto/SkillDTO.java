package com.jayakrishnakalavakuri.portfolio.dto;

// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jayakrishnakalavakuri.portfolio.model.CategoryOption;
import com.jayakrishnakalavakuri.portfolio.model.ProficiencyLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Long id;
    private String name;
    private CategoryOption category;
    private ProficiencyLevel proficiency;
}
