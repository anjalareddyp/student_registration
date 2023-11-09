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
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phone;

    private String email;

    @Column(columnDefinition = "tinyint(1) default 0")
    private int isHeadOfDepartment;

    @OneToOne(cascade = CascadeType.ALL)
   private Department departmentId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

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

    public Instructor(String name, String phone, String email, int isHeadOfDepartment, Department departmentId, List<Course> courses) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isHeadOfDepartment = isHeadOfDepartment;
        this.departmentId = departmentId;
        this.courses = courses;
    }
}
