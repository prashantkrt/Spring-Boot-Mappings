package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One-to-many relationship with orders
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();
}
//Customers Table:
//id   name
//1    John Doe
//
//Orders Table:
//id   order_number   customer_id
//1    One            1
//2    Two            1
//3    Three          1