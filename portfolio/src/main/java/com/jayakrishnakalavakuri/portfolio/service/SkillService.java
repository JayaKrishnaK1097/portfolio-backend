package com.jayakrishnakalavakuri.portfolio.service;

// Imports
import com.jayakrishnakalavakuri.portfolio.model.Skill;
import com.jayakrishnakalavakuri.portfolio.dto.SkillDTO;
import com.jayakrishnakalavakuri.portfolio.repository.SkillRepository;
import com.jayakrishnakalavakuri.portfolio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return convertToDTO(skill);
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = convertToEntity(skillDTO);
        return convertToDTO(skillRepository.save(skill));
    }

    public SkillDTO updateSkill(Long id, SkillDTO updatedSkillDTO) {
        Skill existingSkill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        existingSkill.setName(updatedSkillDTO.getName());
        existingSkill.setCategory(updatedSkillDTO.getCategory());
        existingSkill.setProficiency(updatedSkillDTO.getProficiency());

        return convertToDTO(skillRepository.save(existingSkill));
    }

    public void deleteSkill(Long id) {
        skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        skillRepository.deleteById(id);
    }


    private SkillDTO convertToDTO(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getName(),
                skill.getCategory(),
                skill.getProficiency()
        );
    }

    private Skill convertToEntity(SkillDTO skillDTO) {
        return new Skill(
                null,
                skillDTO.getName(),
                skillDTO.getCategory(),
                skillDTO.getProficiency()
        );
    }
}
