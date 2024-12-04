package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // The @OneToMany side (Course in the example) is an inverse relationship.
    // It doesn't need to maintain the foreign key. It only provides a logical mapping to access the related entities (students) without actually storing a foreign key in the Course table.
    // One-to-many relationship: one course can have many students
    @OneToMany(mappedBy = "course")  // One-to-many relationship with Student
    private Set<Student> students = new HashSet<>();
}

//id  | name
//---------------
//1   | Math
//2   | Science
