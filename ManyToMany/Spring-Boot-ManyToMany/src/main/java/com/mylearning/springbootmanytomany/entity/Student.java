package com.mylearning.springbootmanytomany.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(
            name = "student_course", // Join table
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"), // Foreign key for Student
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id") // Foreign key for Course
    )
    private Set<Course> courses = new HashSet<>();

}
