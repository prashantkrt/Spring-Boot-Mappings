package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laptop_id", referencedColumnName = "id")
    private Laptop laptop;

    // name = "laptop_id": Indicates the foreign key column in the Student table.
    // referencedColumnName = "id": Indicates the primary key column in the Laptop table being referenced.
    // This approach is used on the owning side of the relationship. The owning side is responsible for managing the foreign key.
}
/*

Student Table
-------------------------
id   | name  | laptop_id
-------------------------
1    | Alice | 1

================================================================

Laptop Table
---------------------
id   | brand | model
---------------------
 1    | Dell  | Intel

 */