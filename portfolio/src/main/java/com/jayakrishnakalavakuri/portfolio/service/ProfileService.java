package com.jayakrishnakalavakuri.portfolio.service;

// Imports
import com.jayakrishnakalavakuri.portfolio.dto.ProfileDTO;
import com.jayakrishnakalavakuri.portfolio.model.Profile;
import com.jayakrishnakalavakuri.portfolio.repository.ProfileRepository;
import com.jayakrishnakalavakuri.portfolio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
        return convertToDTO(profile);
    }

    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = convertToEntity(profileDTO);
        return convertToDTO(profileRepository.save(profile));
    }

    public ProfileDTO updateProfile(Long id, ProfileDTO updatedProfileDTO) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
        existingProfile.setFullName(updatedProfileDTO.getFullName());
        existingProfile.setTitle(updatedProfileDTO.getTitle());
        existingProfile.setDescription(updatedProfileDTO.getDescription());
        existingProfile.setEmail(updatedProfileDTO.getEmail());
        existingProfile.setPhotoUrl(updatedProfileDTO.getPhotoUrl());

        return convertToDTO(profileRepository.save(existingProfile));
    }

    public void deleteProfile(Long id) {
        profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
        profileRepository.deleteById(id);
    }

    public ProfileDTO convertToDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getTitle(),
                profile.getDescription(),
                profile.getEmail(),
                profile.getPhotoUrl()
        );
    }

    public Profile convertToEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setFullName(dto.getFullName());
        profile.setTitle(dto.getTitle());
        profile.setDescription(dto.getDescription());
        profile.setEmail(dto.getEmail());
        profile.setPhotoUrl(dto.getPhotoUrl());
        return profile;
    }
}
