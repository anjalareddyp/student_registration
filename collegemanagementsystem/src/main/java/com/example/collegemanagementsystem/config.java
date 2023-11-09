package com.example.collegemanagementsystem;

import com.example.collegemanagementsystem.repo.CourseRepo;
import com.example.collegemanagementsystem.repo.DepartmentRepo;
import com.example.collegemanagementsystem.repo.InstructorRepo;
import com.example.collegemanagementsystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class config implements ApplicationRunner {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
