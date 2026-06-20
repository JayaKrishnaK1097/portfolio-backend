package com.jayakrishnakalavakuri.portfolio.service;

// Imports
import com.jayakrishnakalavakuri.portfolio.model.Profile;
import com.jayakrishnakalavakuri.portfolio.repository.ProfileRepository;
import com.jayakrishnakalavakuri.portfolio.model.Project;
import com.jayakrishnakalavakuri.portfolio.repository.ProjectRepository;
import com.jayakrishnakalavakuri.portfolio.exception.ResourceNotFoundException;
import com.jayakrishnakalavakuri.portfolio.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return convertToDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        return convertToDTO(projectRepository.save(project));
    }

    public ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO) {
        Project existingProject = projectRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        existingProject.setTitle(updatedProjectDTO.getTitle());
        existingProject.setDescription(updatedProjectDTO.getDescription());
        existingProject.setTechStack(updatedProjectDTO.getTechStack());
        existingProject.setGithubUrl(updatedProjectDTO.getGithubUrl());
        existingProject.setLiveUrl(updatedProjectDTO.getLiveUrl());

        return convertToDTO(projectRepository.save(existingProject));
    }

    public void deleteProject(Long id) {
        projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with id: " + id));
        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setTechStack(project.getTechStack());
        dto.setGithubUrl(project.getGithubUrl());
        dto.setLiveUrl(project.getLiveUrl());

        if (project.getProfile() != null) {
            dto.setProfileId(project.getProfile().getId());
        }

        return dto;
    }

    private Project convertToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTechStack(dto.getTechStack());
        project.setGithubUrl(dto.getGithubUrl());
        project.setLiveUrl(dto.getLiveUrl());

        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found by id: " + dto.getProfileId()));
            project.setProfile(profile);
        }

        return project;
    }
}
