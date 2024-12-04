package com.mylearning.springbootmanytomany.service;

import com.mylearning.springbootmanytomany.entity.Course;
import com.mylearning.springbootmanytomany.entity.Student;
import com.mylearning.springbootmanytomany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

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
    }
}
//
//Database Representation
//Student Table:
//id	name
//1	    Alice
//2	    Bob
//3	    Charlie

//Course Table:
//id	title
//1	    Mathematics
//2	    Physics
//3	   Computer Science

//student_course Join Table:
//student_id	course_id
//1          	1
//1	            2
//2          	1
//3	            2
//3         	3

