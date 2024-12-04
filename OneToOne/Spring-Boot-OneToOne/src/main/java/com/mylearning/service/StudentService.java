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
        // laptopRepository.save(laptop); gives persist error
        // detached entity passed to persist: occurs when you try to save an entity that is not in a persistent context (i.e., a "detached" entity)
        // and you attempt to persist it without proper association to a parent entity.
        // If you don't use cascading, you would need to manually save the Laptop before saving the Student

        Student student = new Student();
        student.setName("Alice");
        student.setLaptop(laptop);
        //Automatically saves the associated Laptop entity when you save the Student entity, due to Cascading Type set to ALL
        studentRepository.save(student); // Saves both Student and Laptop
    }
}

//Student Table
//id	name	laptop_id
//1	   Alice	1

//Laptop Table
//id	brand	model
//1	   Dell	   Intel
