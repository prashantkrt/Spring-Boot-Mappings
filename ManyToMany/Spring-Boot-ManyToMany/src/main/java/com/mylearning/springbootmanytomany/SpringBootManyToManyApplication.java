package com.mylearning.springbootmanytomany;

import com.mylearning.springbootmanytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootManyToManyApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootManyToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentService.createStudentsAndCourses();
    }
}
