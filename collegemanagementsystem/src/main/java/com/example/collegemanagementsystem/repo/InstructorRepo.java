package com.example.collegemanagementsystem.repo;

import com.example.collegemanagementsystem.modal.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
}
