package com.example.SunbaseBackend.Repository;

import com.example.SunbaseBackend.Model.Admin;
import com.example.SunbaseBackend.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,String> {
    Optional<Admin> findFirstByEmail(String email);
}
