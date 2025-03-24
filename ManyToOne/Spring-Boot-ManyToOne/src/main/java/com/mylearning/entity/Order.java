package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    // Many-to-one relationship with Customer
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
}

/*
Customers Table:
        +----+-----------+
        | ID | Name      |
        +----+-----------+
        |  1 | John Doe  |
        +----+-----------+

Orders Table:
        +----+--------------+-------------+
        | ID | Order_Number | Customer_ID |
        +----+--------------+-------------+
        |  1 | One         |      1       |
        |  2 | Two         |      1       |
        |  3 | Three       |      1       |
        +----+--------------+-------------+
 */

// Note Very Important
// In JPA, @JoinColumn is used to define the foreign key column in a
// One-to-One (@OneToOne) or Many-to-One (@ManyToOne) relationship.

//If we attempt to place the foreign key in the Customer table instead of the Orders table, it creates a non-relational and non-scalable structure.

//Incorrect structure
/*
+----+----------+------------+
| id | name     | order_ids  |
+----+----------+------------+
| 1  | John Doe | 1,2,3      |
| 2  | Alice    | 4,5        |
+----+----------+------------+
 */

//will look like this if we try to make it this way
//A single column (order_ids) holds multiple values (1,2,3), which violates First Normal Form (1NF).
//SQL databases do not support multiple foreign keys stored in a single column like this.
//JPA Cannot Store Multiple Foreign Keys in One Row:

/*

Customer Table (Correct)
+----+----------+
| id | name     |
+----+----------+
| 1  | John Doe |
| 2  | Alice    |
+----+----------+

Orders Table (Correct)
+----+--------------+-------------+
| id | order_number | customer_id |
+----+--------------+-------------+
| 1  | One         | 1            |
| 2  | Two         | 1            |
| 3  | Three       | 1            |
| 4  | Four        | 2            |
| 5  | Five        | 2            |
+----+--------------+-------------+
 */

//This is why JPA enforces that foreign keys should always be maintained on the "many" side of a One-to-Many relationship!