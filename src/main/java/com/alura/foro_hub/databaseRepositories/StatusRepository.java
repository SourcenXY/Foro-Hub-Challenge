package com.alura.foro_hub.databaseRepositories;

import com.alura.foro_hub.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
