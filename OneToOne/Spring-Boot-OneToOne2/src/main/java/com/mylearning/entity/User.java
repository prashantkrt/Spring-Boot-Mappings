package com.mylearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Will maintain the foreign key
    // This means all the following JPA operations (persist, merge, remove, refresh, detach) applied to the User entity will be cascaded to the associated Address entity.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

//The Address entity is the inverse side (mappedBy = "address") → It does not maintain the foreign key.
//The User entity owns the relationship (@JoinColumn(name = "address_id")) → It maintains the foreign key.