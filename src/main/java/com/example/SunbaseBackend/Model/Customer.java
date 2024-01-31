package com.example.SunbaseBackend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    Integer id;
    String first_name;
    String last_name;
    String street;
    String address;
    String city;
    String state;
    @Email
    String email;
    String phone;
}
