package com.example.SunbaseBackend.Repository;

import com.example.SunbaseBackend.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer,Integer> {

}
