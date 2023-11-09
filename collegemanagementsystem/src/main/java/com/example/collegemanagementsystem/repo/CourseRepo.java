package com.example.collegemanagementsystem.repo;

import com.example.collegemanagementsystem.modal.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
}
