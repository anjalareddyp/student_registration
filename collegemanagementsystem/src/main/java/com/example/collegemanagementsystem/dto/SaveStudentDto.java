package com.example.collegemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveStudentDto {

    private String name;

    private String registrationNo;

    private String email;

    private String phone;

    private List<Integer> courses = new ArrayList<>();
}
