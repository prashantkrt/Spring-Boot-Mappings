package com.mylearning;

import com.mylearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootManyToOne2Application implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootManyToOne2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentService.createStudentWithCourse();
    }
}
