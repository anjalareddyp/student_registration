package com.example.collegemanagementsystem.modal;

import com.example.collegemanagementsystem.dto.StudentCourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "student_course")
public class Student_Course {
  @EmbeddedId
    private StudentCourseDto student_course_id;
}
