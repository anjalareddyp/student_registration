package com.example.collegemanagementsystem.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseCode;

    private String courseDescription;

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
