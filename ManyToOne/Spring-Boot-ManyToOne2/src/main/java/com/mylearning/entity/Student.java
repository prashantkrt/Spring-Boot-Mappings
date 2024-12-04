package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many-to-one relationship: many students can belong to one course
    // CascadeType.ALL ensures the Course is saved when a Student is saved
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")  // Foreign key to the Course table
    private Course course;
}


//id  | name   | course_id (FK)
//--------------------------------
//1   | Alice  | 1
//2   | Bob    | 1
//3   | Charlie| 2