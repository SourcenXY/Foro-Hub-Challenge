package com.alura.foro_hub.databaseRepositories;

import com.alura.foro_hub.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
