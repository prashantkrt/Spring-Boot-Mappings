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

//Customers Table:
//id   name
//1    John Doe
//
//Orders Table:
//id   order_number   customer_id
//1    One            1
//2    Two            1
//3    Three          1