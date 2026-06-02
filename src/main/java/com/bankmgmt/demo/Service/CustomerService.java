package com.bankmgmt.demo.Service;

import com.bankmgmt.demo.Entity.Customer;
import com.bankmgmt.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

// TO save customer
    public Customer saveCustomer(Customer customer){

          return customerRepository.save(customer);
    }
// find customer using customer id
    public Optional<Customer> getCustomerById(Integer id){

        return customerRepository.findById(id);
    }

    // To update customer detail

    public Customer updateCustomer(Integer id , Customer customer){

         Customer exisitingCustomer = customerRepository.findById(id).orElseThrow(  () -> new RuntimeException("Customer not found "));

         exisitingCustomer.setCusName(customer.getCusName());
         exisitingCustomer.setEmail(customer.getEmail());
         exisitingCustomer.setPhone(customer.getPhone());
         exisitingCustomer.setAddress(customer.getAddress());


        return customerRepository.save(customer);

    }

    //find list of all customer
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    // used to delete customer by id
    public void deleteById(Integer id){

        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }else{
            throw new RuntimeException("Customer not found");
        }
    }

}
