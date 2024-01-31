package com.example.SunbaseBackend.Controller;

import com.example.SunbaseBackend.Model.Admin;
import com.example.SunbaseBackend.Model.Customer;
import com.example.SunbaseBackend.Model.JwtRequest;
import com.example.SunbaseBackend.Service.JwtHelper;
import com.example.SunbaseBackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager manager;

    @PostMapping("/addNewAdmin")
    public String addNewAdmin(@RequestBody Admin admin) {
        return adminService.addUser(admin);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return adminService.getAllCustomers();
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtHelper.generateToken(jwtRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/add/customer")
    public String addCustomer(@RequestBody Customer customer){
        return adminService.addCustomer(customer);
    }

    @PutMapping("/edit/customer")
    public String updateCustomer(@RequestBody Customer customer){
        return adminService.updateCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Integer id){
        return adminService.getCustomerById(id);
    }

    @DeleteMapping("/delete/customer")
    public String deleteCustomer(@RequestParam Integer id){
        return adminService.deleteCustomer(id);
    }

}
