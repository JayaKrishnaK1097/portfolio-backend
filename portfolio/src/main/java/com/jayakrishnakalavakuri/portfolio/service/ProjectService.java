package com.jayakrishnakalavakuri.portfolio.service;

// Imports
import com.jayakrishnakalavakuri.portfolio.model.Project;
import com.jayakrishnakalavakuri.portfolio.repository.ProjectRepository;
import com.jayakrishnakalavakuri.portfolio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject){
        Project existingProject = getProjectById(id);

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setTechStack(updatedProject.getTechStack());
        existingProject.setGithubUrl(updatedProject.getGithubUrl());
        existingProject.setLiveUrl(updatedProject.getLiveUrl());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }
}
