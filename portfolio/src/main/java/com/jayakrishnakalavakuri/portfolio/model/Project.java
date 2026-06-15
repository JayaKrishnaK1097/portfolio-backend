package com.jayakrishnakalavakuri.portfolio.model;

// Imports
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.loadtime.definition.Definition;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String techStack;
    private String githubUrl;
    private String liveUrl;
}
