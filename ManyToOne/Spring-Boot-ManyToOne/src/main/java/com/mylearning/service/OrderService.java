package com.mylearning.service;

import com.mylearning.entity.Customer;
import com.mylearning.entity.Order;
import com.mylearning.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder() {

        Customer customer = new Customer();
        customer.setName("John Doe");

        Order order1 = new Order();
        order1.setOrderNumber("One");
        order1.setCustomer(customer);

        Order order2 = new Order();
        order2.setOrderNumber("Two");
        order2.setCustomer(customer);

        Order order3 = new Order();
        order3.setOrderNumber("Three");
        order3.setCustomer(customer);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }


}
/*
Table Structure

Customers Table:
id   name
1    John Doe

Orders Table:
id   order_number   customer_id
1    One            1
2    Two            1
3    Three          1

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