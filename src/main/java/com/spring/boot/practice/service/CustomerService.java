package com.spring.boot.practice.service;

import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangchong on 9/26/16.
 */

@Service
public class CustomerService {

    private static final Class CUSTOMER_CLASS = Customer.class;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    /*public int update(Query query, Update update, String collectionName){
        return mongoTemplate.updateMulti(query,update, collectionName).getN();
    }*/

    public int update(Query query, Update update){
        return mongoTemplate.updateMulti(query, update, Collections.customer.name()).getN();
    }

    public Customer findOne(String id){
        return customerRepository.findOne(id);
    }

    public List<Customer> find(Query query){
        return mongoTemplate.find(query,Customer.class, Collections.customer.name());
    }

    public void save(Customer customer){
        mongoTemplate.save(customer);
    }

    public <T> MapReduceResults<T> mapReduce(Query query, String mapFunction, String reduceFunction, Class<T> entityClass){
        //return mongoTemplate.mapReduce("customer",mapFunction,reduceFunction,entityClass);
        return mongoTemplate.mapReduce(query, Collections.customer.name(),mapFunction, reduceFunction, entityClass);
    }

    public <T> MapReduceResults<T> mapReduce(Query query, String mapFunction, String reduceFunction, String collectionName, Class<T> entityClass){
        //return mongoTemplate.mapReduce("customer",mapFunction,reduceFunction,entityClass);
        return mongoTemplate.mapReduce(query, Collections.customer.name(),mapFunction, reduceFunction, MapReduceOptions.options().outputCollection(collectionName), entityClass);
    }

}
