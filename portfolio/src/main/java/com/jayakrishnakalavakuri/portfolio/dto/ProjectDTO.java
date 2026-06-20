package com.jayakrishnakalavakuri.portfolio.dto;

// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String title;
    private String description;
    private String techStack;
    private String githubUrl;
    private String liveUrl;
    private Long profileId;
}
