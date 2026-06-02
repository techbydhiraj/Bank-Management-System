package com.bankmgmt.demo.Repository;

import com.bankmgmt.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository< Customer , Integer> {


}
