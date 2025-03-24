package com.mylearning.springbootmanytomany.service;

import com.mylearning.springbootmanytomany.entity.Course;
import com.mylearning.springbootmanytomany.entity.Student;
import com.mylearning.springbootmanytomany.repository.CourseRepository;
import com.mylearning.springbootmanytomany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void createStudentsAndCourses() {
        // Create many Courses
        Course math = new Course();
        math.setTitle("Mathematics");

        Course physics = new Course();
        physics.setTitle("Physics");

        Course cs = new Course();
        cs.setTitle("Computer Science");

        // Create many Students
        Student alice = new Student();
        alice.setName("Alice");
        alice.getCourses().add(math);
        alice.getCourses().add(physics);

        Student bob = new Student();
        bob.setName("Bob");
        bob.getCourses().add(math);

        Student charlie = new Student();
        charlie.setName("Charlie");
        charlie.getCourses().add(physics);
        charlie.getCourses().add(cs);

        // Save Students (Courses will also be saved due to CascadeType.ALL)
        studentRepository.save(alice);
        studentRepository.save(bob);
        studentRepository.save(charlie);

        Student student = studentRepository.findById(1L).orElseThrow();
        Set<Course> courses = student.getCourses();

        for (Course course : courses) {
            System.out.println(course.getTitle()); // Mathematics, Physics
        }

        //we can also fetch student from course even though it doesn't uses any join table
        Course course = courseRepository.findById(1).orElseThrow();
        Set<Student> students = course.getStudents();

        for (Student stu : students) {
            System.out.println(stu.getName()); // Alice, Bob
        }

        //Remember:=>
        //JPA does NOT store foreign keys directly in Student or Course for a @ManyToMany relationship.
        //Instead, it creates a separate join table (student_course) to maintain the mapping.
        //When you call course.getStudents(), Hibernate generates a query to fetch students from the student_course table using course_id.
    }


}
/*
Database Representation
STUDENT TABLE:
+----+---------+
| id |  name   |
+----+---------+
|  1 | Alice   |
|  2 | Bob     |
|  3 | Charlie |
+----+---------+

COURSE TABLE:
+----+------------------+
| id |      title       |
+----+------------------+
|  1 | Mathematics      |
|  2 | Physics          |
|  3 | Computer Science |
+----+------------------+

STUDENT_COURSE (Join Table):
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

