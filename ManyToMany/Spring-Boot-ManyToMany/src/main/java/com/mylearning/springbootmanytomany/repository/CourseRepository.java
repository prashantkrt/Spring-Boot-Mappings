package com.mylearning.springbootmanytomany.repository;

import com.mylearning.springbootmanytomany.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
