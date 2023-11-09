package com.example.collegemanagementsystem.modal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String building;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

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
}
