package com.mylearning.springbootmanytomany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
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

//If JPA somehow allowed @JoinColumn in @ManyToMany, the table would need a way to store multiple values in a single column (which relational databases don't support).
// This would result in a bad database design like this:
//if we use this way
//@ManyToMany
//@JoinColumn(name = "course_id") // ❌ Incorrect usage
//private List<Course> courses;

/*

❌ Incorrect Student Table (Hypothetical, if allowed)
STUDENT TABLE:
+----+---------+------------+
| id |  name   | course_id  |
+----+---------+------------+
|  1 | Alice   | 1,2        |  ❌ Multiple course IDs in one column (not allowed)
|  2 | Bob     | 1          |
|  3 | Charlie | 2,3        |  ❌ Multiple values in one column
+----+---------+------------+

❌ Incorrect Course Table (Hypothetical, if allowed)
COURSE TABLE:
+----+------------------+-----------+
| id |      title      | student_id |
+----+------------------+-----------+
|  1 | Mathematics     | 1,2        |  ❌ Multiple student IDs in one column
|  2 | Physics         | 1,3        |  ❌ Not a valid relational database structure
|  3 | Computer Science| 3          |
+----+------------------+-----------+

 */

// ✅ Correct Approach (Using @JoinTable in Many-to-Many)
// @ManyToMany
// @JoinTable(
//     name = "student_course",
//     joinColumns = @JoinColumn(name = "student_id"),
//     inverseJoinColumns = @JoinColumn(name = "course_id")
// )
// private List<Course> courses;

/*
How the Tables Should Actually Look (Correct)
+----+---------+
| id |  name   |
+----+---------+
|  1 | Alice   |
|  2 | Bob     |
|  3 | Charlie |
+----+---------+

+----+------------------+
| id |      title       |
+----+------------------+
|  1 | Mathematics      |
|  2 | Physics          |
|  3 | Computer Science |
+----+------------------+

+------------+-----------+
| student_id | course_id |
+------------+-----------+
|     1      |     1     |
|     1      |     2     |
|     2      |     1     |
|     3      |     2     |
|     3      |     3     |
+------------+-----------+
 */