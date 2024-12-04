package com.mylearning.service;

import com.mylearning.entity.Address;
import com.mylearning.entity.User;
import com.mylearning.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void createUser() {
        Address address = new Address();
        address.setStreet("Elm Street");
        address.setCity("Springfield");

        User user = new User();
        user.setName("John");
        user.setAddress(address);

        userRepo.save(user); // Saves both User and Address due to CascadeType.ALL
    }
}
