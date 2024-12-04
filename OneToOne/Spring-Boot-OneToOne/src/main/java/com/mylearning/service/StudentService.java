package com.mylearning.service;

import com.mylearning.dao.LaptopRepository;
import com.mylearning.dao.StudentRepository;
import com.mylearning.entity.Laptop;
import com.mylearning.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final LaptopRepository laptopRepository;

    public StudentService(StudentRepository studentRepository, LaptopRepository laptopRepository) {
        this.studentRepository = studentRepository;
        this.laptopRepository = laptopRepository;
    }

    public void createStudentWithLaptop() {
        //making 1-1 mapping
        //1 Laptop for 1 student only

        //creating the laptop object
        Laptop laptop = new Laptop();
        laptop.setBrand("Dell");
        laptop.setModel("Intel");
        laptopRepository.save(laptop);

        Student student = new Student();
        student.setName("Alice");
        student.setLaptop(laptop);
        studentRepository.save(student); // Saves both Student and Laptop

    }
}
