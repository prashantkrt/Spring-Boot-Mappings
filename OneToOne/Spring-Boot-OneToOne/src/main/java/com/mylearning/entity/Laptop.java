package com.mylearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
}
/*
Student Table
------------------------
id   | name  | laptop_id
-------------------------
1    | Alice | 1


==================================

Laptop Table
--------------------
id   | brand | model
--------------------
 1   | Dell  | Intel

 */