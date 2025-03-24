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
            name = "student_course", // Join table or Intermediate table
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"), // Foreign key for Student
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id") // Foreign key for Course
    )
    private Set<Course> courses = new HashSet<>();

}

// @JsonManagedReference on the owning side (the side that contains the @JoinTable).
// @JsonBackReference on the inverse side (the side that is mapped by the owning entity).

//@ManyToMany
//@JoinColumn(name = "course_id") // ❌ This is incorrect in Many-to-Many
//private List<Course> courses;

//Problem:
//This approach tries to store multiple course IDs in a single column of the Student table.
//This violates First Normal Form (1NF) because a column should store only atomic values, not multiple IDs.

//❌ @JoinColumn alone does NOT work in @ManyToMany because:
//It cannot store multiple foreign keys in a single column.
//A join table is necessary to properly map the relationship.

// When Can We Use @JoinColumn Without @JoinTable?
// You can use @JoinColumn in:
//✅ One-to-One (@OneToOne)
//✅ Many-to-One (@ManyToOne)

/*
STUDENT TABLE:
id   name
1    Alice
2    Bob
3    Charlie

COURSE TABLE:
id   course_name
101  Math
102  Physics
103  Chemistry

STUDENT_COURSE TABLE (Join Table):
student_id   course_id
1            101
1            102
2            101
2            103
3            102
3            103

 */