package com.example.collegemanagementsystem.repo;

import com.example.collegemanagementsystem.dto.StudentCourseDto;
import com.example.collegemanagementsystem.modal.Student;
import com.example.collegemanagementsystem.modal.Student_Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Student_CourseRepo extends JpaRepository<Student_Course, StudentCourseDto> {
}
