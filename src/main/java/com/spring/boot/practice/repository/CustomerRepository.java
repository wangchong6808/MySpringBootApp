package com.spring.boot.practice.repository;

import com.spring.boot.practice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by wangchong on 9/24/16.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public List<Customer> findByName(String name);
    public List<Customer> findByAddress(String address);

}