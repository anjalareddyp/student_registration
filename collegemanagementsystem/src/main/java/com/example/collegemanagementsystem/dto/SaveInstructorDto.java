package com.example.collegemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SaveInstructorDto {

    private String name;

    private String phone;

    private String email;

    private int isHeadOfDepartment;

    private int departmentId;

    List<Integer> courses = new ArrayList<>();



}
