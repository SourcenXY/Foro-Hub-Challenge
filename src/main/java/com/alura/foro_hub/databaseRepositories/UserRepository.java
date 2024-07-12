package com.alura.foro_hub.databaseRepositories;

import com.alura.foro_hub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}