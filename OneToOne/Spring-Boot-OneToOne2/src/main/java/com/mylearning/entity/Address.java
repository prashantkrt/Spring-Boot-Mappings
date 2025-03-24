package com.mylearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;

    // inverse side, and it won't be maintaining the foreign key
    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
}
//note:
//cascade = CascadeType.ALL in the @OneToOne(mappedBy = "address", cascade = CascadeType.ALL) annotation inside the Address entity is unnecessary and redundant.
//The User entity has @JoinColumn(name = "address_id", referencedColumnName = "id"), which means it maintains the foreign key relationship.
//Any cascading operations (persist, merge, remove, refresh, detach) performed on User will be applied to Address.
//The Address entity has @OneToOne(mappedBy = "address"), meaning it does not own the foreign key.
//It does not control the relationship, so cascading from Address to User is not needed.


//The Address entity is the inverse side (mappedBy = "address") → It does not maintain the foreign key.
//The User entity owns the relationship (@JoinColumn(name = "address_id")) → It maintains the foreign key.