package com.example.collegemanagementsystem.controller;

import com.example.collegemanagementsystem.dto.SaveInstructorDto;
import com.example.collegemanagementsystem.dto.SaveStudentDto;
import com.example.collegemanagementsystem.dto.StudentCourseDto;
import com.example.collegemanagementsystem.dto.UpdateStudentDto;
import com.example.collegemanagementsystem.modal.Course;
import com.example.collegemanagementsystem.modal.Department;
import com.example.collegemanagementsystem.modal.Instructor;
import com.example.collegemanagementsystem.modal.Student;
import com.example.collegemanagementsystem.repo.*;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class CMSController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private Student_CourseRepo student_courseRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/allStudents")
    public ResponseEntity allStudents(){
        List<Student> students = studentRepo.findAll();
        return new ResponseEntity(students, HttpStatus.OK);
    }

    @GetMapping("/allCourses")
    public ResponseEntity allCourses(){
        List<Course> courses = courseRepo.findAll();
        return new ResponseEntity(courses, HttpStatus.OK);
    }

    @GetMapping("/allInstructors")
    public ResponseEntity allInstructors(){
        List<Instructor> instructors = instructorRepo.findAll();
        return new ResponseEntity(instructors, HttpStatus.OK);
    }

    @GetMapping("/allDepartments")
    public ResponseEntity allDepartments(){

        List<Department> departments = departmentRepo.findAll();
        List<HashMap<Object, Object>> response =  new ArrayList<>();
        for(Department department : departments){
            HashMap<Object, Object> dep = new HashMap<>();
            Set<Course> courses = department.getCourses();
                List<Instructor> instructors = instructorRepo.findAll();
                dep.put("isHeadOfTheDepartmentAvailable",false);
                for(Instructor instructor : instructors){
                    if(instructor.getDepartmentId().getId() == department.getId()){
                        if(instructor.getIsHeadOfDepartment() == 1){
                            dep.put("isHeadOfTheDepartmentAvailable",true);
                        }
                    }
                    for(Course instructorCourse : instructor.getCourses()){
                        if(courses.contains(instructorCourse)){
                            courses.remove(instructorCourse);
                        }
                    }
                }
                department.setCourses(courses);
                dep.put(department.getId(), department);
                response.add(dep);

        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/student/allCourses/{studentId}")
    public ResponseEntity allCoursesOfStudent(@PathVariable("studentId") Integer studentId){
        Student student = studentRepo.findById(studentId).get();
        return new ResponseEntity(student.getCourses(), HttpStatus.OK);

    }
    @GetMapping("/student/studentDetails/{studentId}")
    public ResponseEntity studentDetails(@PathVariable("studentId") Integer studentId){
        Student student = studentRepo.findById(studentId).get();
        return new ResponseEntity(student, HttpStatus.OK);

    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity getInstructorById(@PathVariable("instructorId") Integer instructorId){
       Instructor instructor = instructorRepo.findById(instructorId).get();
        return new ResponseEntity(instructor, HttpStatus.OK);
    }


    @GetMapping("/instructor/courses/{instructorId}")
    public ResponseEntity getInstructorCoursesById(@PathVariable("instructorId") Integer instructorId){
        Instructor instructor = instructorRepo.findById(instructorId).get();
        return new ResponseEntity(instructor.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity getDepartmentById(@PathVariable("departmentId") Integer departmentId){
        Department department = departmentRepo.findById(departmentId).get();
        return new ResponseEntity(department, HttpStatus.OK);
    }

    @PostMapping("/save/student")
    public ResponseEntity saveStudent(@RequestBody SaveStudentDto saveStudentDto){
        System.out.println( saveStudentDto.toString());
        List<Course> courses = new ArrayList<>();
        for(int courseId : saveStudentDto.getCourses()){
            Course course = courseRepo.findById(courseId).get();
            System.out.println(course.toString());
            courses.add(course);
        }
        Student student = new Student(saveStudentDto.getName(), saveStudentDto.getRegistrationNo(), saveStudentDto.getEmail(), saveStudentDto.getPhone(), courses);
        System.out.println(student.toString());
        student = studentRepo.save(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @PostMapping("/save/instructor")
    public ResponseEntity saveInstructor(@RequestBody SaveInstructorDto saveInstructorDto){
        List<Course> courses = new ArrayList<>();
        System.out.println(saveInstructorDto.toString());
        for(int courseId : saveInstructorDto.getCourses()){
            Course course = courseRepo.findById(courseId).get();
            courses.add(course);
        }
        Department department = departmentRepo.findById(saveInstructorDto.getDepartmentId()).get();
        Instructor instructor = new Instructor(saveInstructorDto.getName(), saveInstructorDto.getPhone(), saveInstructorDto.getEmail(), saveInstructorDto.getIsHeadOfDepartment(), department, courses);
       instructor =  instructorRepo.save(instructor);
        return new ResponseEntity(instructor, HttpStatus.OK);
    }


    @GetMapping("/department/courses/{departmentId}")
    public ResponseEntity getAllCoursesOfDepartment(@PathVariable("departmentId") Integer departmentId){
        Department department = departmentRepo.findById(departmentId).get();
        HashMap<Object, Object> response = new HashMap<>();
        List<Instructor> instructors = instructorRepo.findAll();
        Set<Course> courses = department.getCourses();
        boolean isHeadOfTheDepartment = false;
        response.put("isHeadOfTheDepartmentAvailable", "true");
        for(Instructor instructor : instructors){
            if(instructor.getDepartmentId().getId() == departmentId && !isHeadOfTheDepartment && instructor.getIsHeadOfDepartment() == 1){
                response.put("isHeadOfTheDepartmentAvailable", "false");
                isHeadOfTheDepartment = true;
            }
            for(Course course : instructor.getCourses()){
                if(courses.contains(course)){
                    courses.remove(course);
                }
            }
        }

        response.put("courses",courses);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/instructor/availableCourses/{departmentId}")
    public ResponseEntity availableCoursesForInstructor(@PathVariable("departmentId") Integer departmentId){
        HashMap<Object, Object> response = new HashMap<>();
        List<Instructor> instructors = instructorRepo.findAll();
        response.put("isHeadOfTheDepartmentAvailable", "true");
        for(Instructor instructor : instructors){
            if(instructor.getDepartmentId().getId() == departmentId && instructor.getIsHeadOfDepartment() == 1){
                response.put("isHeadOfTheDepartmentAvailable", "false");
                break;
            }
        }
        Set<Course> courses = departmentRepo.findById(departmentId).get().getCourses();
        for(Instructor instructor : instructors){
            for(Course course : instructor.getCourses()){
                if(courses.contains(course)){
                    courses.remove(course);
                }
            }
        }

        response.put("courses",courses);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/studentcourse/delete/{studentId}/{courseId}")
    public ResponseEntity deleteStudentCourse(@PathVariable("studentId") Integer studentId,
                                           @PathVariable("courseId") Integer courseId) {
        try {
            StudentCourseDto Student_Course = new StudentCourseDto(studentId, courseId);

            student_courseRepo.deleteById(Student_Course);
            return new ResponseEntity("Successfully Deleted", HttpStatus.OK);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PutMapping ("/update/student")
    public ResponseEntity updateStudent(@RequestBody SaveStudentDto saveStudentDto,
                                        @RequestParam(name = "id", required = true) final String id) throws Exception {
        System.out.println( saveStudentDto.toString());
        List<Course> courses = new ArrayList<>();
        for(int courseId : saveStudentDto.getCourses()){
            Course course = courseRepo.findById(courseId).get();
            System.out.println(course.toString());
            courses.add(course);
        }
        Student student = new Student(saveStudentDto.getName(), saveStudentDto.getRegistrationNo(), saveStudentDto.getEmail(), saveStudentDto.getPhone(), courses);
        System.out.println(student.toString());
        student = studentRepo.save(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }

}
