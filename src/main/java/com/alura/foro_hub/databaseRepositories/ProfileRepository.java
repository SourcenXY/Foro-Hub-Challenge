package com.alura.foro_hub.databaseRepositories;

import com.alura.foro_hub.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
