package com.example.SunbaseBackend.Service;

import com.example.SunbaseBackend.Model.Admin;
import com.example.SunbaseBackend.Model.Customer;
import com.example.SunbaseBackend.Repository.IAdminRepo;
import com.example.SunbaseBackend.Repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminService implements UserDetailsService{

    @Autowired
    private IAdminRepo adminRepo;

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findFirstByEmail(username);
        return admin.map(AdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(Admin admin) {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        adminRepo.save(admin);
        return "User Added Successfully";
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public String addCustomer(Customer customer) {
        customerRepo.save(customer);
        return "Customer Added";
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepo.findById(id);
    }

    public String deleteCustomer(Integer id) {
        Customer customer = customerRepo.findById(id).orElse(null);

        if(customer == null) return "Customer Does Not Exist";

        customerRepo.deleteById(id);
        return "Customer Deleted";
    }

    public String updateCustomer(Customer customer) {
        customerRepo.save(customer);
        return "Customer updated";
    }
}
