package com.spring.boot.practice.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.spring.boot.practice.MongoDBClient;
import com.spring.boot.practice.model.Contact;
import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.service.CustomerService;
import com.spring.boot.practice.service.ValueObject;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.data.mongodb.core.query.Criteria.where;


/**
 * Created by wangchong on 9/18/16.
 * http://localhost:8080/app/customer/11
 */
@RestController
@RequestMapping(value="/app/customer")
public class CustomerRestController {

    private static final Logger logger = Logger.getLogger(CustomerRestController.class);

    @Autowired
    CustomerService customerService;



    @RequestMapping(value="/{customer_id}", method=RequestMethod.GET)
    public Customer getUser(@PathVariable Long customer_id, @ModelAttribute Customer customer) {
        customer = customerService.findOne(String.valueOf(customer_id));

        return customer;
    }

    @RequestMapping(value="/{customer_id}", method=RequestMethod.PUT)
    public Customer updateUser(@PathVariable Long customer_id, @ModelAttribute Customer customer) {
        List<Customer> customers = customerService.find(new Query(where("name").is("Jack").andOperator(where("address").is("my address is Beijing."))));
        if (customers!=null && customers.size()>0){
            Customer customer1 = customers.get(0);
            System.out.println(customer1.getName());

        }
        Update update = new Update();
        update.set("address","XiAn");
        update.set("name","Tom");
        int updatedCount = customerService.update(new Query(where("_id").is("125")), update);
        System.out.println(updatedCount);

        /*collection.find(and(eq("type", "database"), lte("count", 1))).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println(document.toJson());
            }
        });
        collection.find(Document.parse(""));*/
        //System.out.println(document.toJson());
        //mongoTemplate.find
        return customer;
    }

    @RequestMapping("/calculate")
    public String calculate(){
        MapReduceResults<ValueObject> results = customerService.mapReduce(new Query(where("name").is("Tom")),"classpath:customer_map.js", "classpath:customer_reduce.js","map_reduce_output", ValueObject.class);
        for (ValueObject valueObject : results) {
            System.out.println(valueObject);
        }
        return "ok";
    }

    @RequestMapping("/create")
    public Customer createCustomer(@ModelAttribute Customer customer){
        if(customer.getName()==null){
            customer.setName("sample");
        }
        if(customer.getIntroduction()==null){
            customer.setIntroduction("I am a sample customer.");
        }
        if(customer.getId()==null){
            customer.setId(String.valueOf(new Random(10).nextInt()));
        }
        if(customer.getAddress()==null){
            customer.setAddress("my address is Beijing.");
        }
        if (customer.getContacts()==null || customer.getContacts().size()==0){
            List<Contact> contacts = new ArrayList<>();
            Contact contact = new Contact();
            contact.setName(customer.getName());
            contact.setTel("138119113");
            contacts.add(contact);
            contact = new Contact();
            contact.setName("Linda");
            contact.setTel("08212333");
            contacts.add(contact);
            customer.setContacts(contacts);
        }
        customerService.save(customer);
        //mongoTemplate.mapReduce()
        return customer;
    }
}
