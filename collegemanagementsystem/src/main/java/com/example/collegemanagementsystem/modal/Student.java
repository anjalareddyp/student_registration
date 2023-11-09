package com.example.collegemanagementsystem.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String registrationNo;

    private String email;

    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Course> courses = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @PrePersist
    public void prePersist(){
        this.addedOn = new Date();
        this.updatedOn = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedOn = new Date();
    }

    public Student(String name, String registrationNo, String email, String phone, List<Course> courses) {
        this.name = name;
        this.registrationNo = registrationNo;
        this.email = email;
        this.phone = phone;
        this.courses = courses;

    }
}
