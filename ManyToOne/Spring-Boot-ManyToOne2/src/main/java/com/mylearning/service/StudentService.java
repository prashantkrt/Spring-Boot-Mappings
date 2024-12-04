package com.mylearning.service;

import com.mylearning.entity.Course;
import com.mylearning.entity.Student;
import com.mylearning.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void createStudentWithCourse() {
        // Create a new course
        Course course = new Course();
        course.setName("Mathematics");

        // Create a new student and assign the course
        Student student = new Student();
        student.setName("Alice");
        student.setCourse(course);  // Assign course to the student

        Student student2 = new Student();
        student2.setName("Bob");
        student2.setCourse(course);

        // Save the student (course will be saved automatically due to CascadeType.ALL)
        studentRepository.save(student);
        studentRepository.save(student2);
    }
}

//Student
//id  | name   | course_id (FK)
//--------------------------------
//1   | Alice  | 1
//2   | Bob    | 1
//3   | Charlie| 2

//Course
//id  | name
//---------------
//1   | Math
//2   | Science
