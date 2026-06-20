package com.jayakrishnakalavakuri.portfolio.repository;

//Imports
import com.jayakrishnakalavakuri.portfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT DISTINCT p FROM Profile p LEFT JOIN FETCH p.projects")
    List<Profile> findAllWithProjects();
}