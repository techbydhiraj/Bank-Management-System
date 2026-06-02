package com.bankmgmt.demo.Controller;

import com.bankmgmt.demo.Entity.Customer;
import com.bankmgmt.demo.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerController {


     @Autowired
     private CustomerService customerService;

     @PostMapping("/save")
     public Customer saveCustomer( @Valid @RequestBody Customer customer){

         return  customerService.saveCustomer(customer);
     }

     @GetMapping("/{id}")
     public Optional<Customer> getCustomerById(@PathVariable Integer id){

         return customerService.getCustomerById(id);
     }

     @GetMapping("/all")
     public List<Customer> getAllCustomer(){

         return  customerService.getAllCustomers();
     }

     @DeleteMapping("/{id}")
     public String deleteById(@PathVariable Integer id) {
         customerService.deleteById(id);
         return "customer deleted successfully";
     }

     @PutMapping("/{id}")
     public Customer updateCustomer(@PathVariable Integer id ,@Valid @RequestBody Customer customer){

         return customerService.updateCustomer(id,customer);
     }


}
