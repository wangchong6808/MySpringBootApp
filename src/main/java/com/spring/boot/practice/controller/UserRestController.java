package com.spring.boot.practice.controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.model.User;
import com.spring.boot.practice.service.MyRestTemplate;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by wangchong on 9/18/16.
 */
@RestController
@RequestMapping(value="/app/users")
public class UserRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    private static final Logger logger = Logger.getLogger(UserRestController.class);

    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long user) {
        // ...
        User myuser = new User();
        myuser.setId(String.valueOf(user));
        myuser.setName("Jack");
        return myuser;
    }

    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long user) {
        // ...
        return null;
    }

    @RequestMapping(value="/{user}", method= RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user) {
        // ...
        return null;
    }

    @RequestMapping(value="/greeting/{name}", method=RequestMethod.GET)
    public User greetUser(@PathVariable String name, HttpEntity<byte[]> requestEntity, @ModelAttribute User user){
        logger.info("greetUser method called......");

        //user.setIntroduction("this is newly created user.");
        return user;
        //return getUser(111l);
    }


    @ModelAttribute
    public User createUser(@RequestParam(required = false) String name){
        logger.info("createUser method called......");
        //return "{message:\"Hello "+user.getName()+"!\"}";
        //return getUser(111l);
        User user = new User();
        user.setName(name);
        user.setId(String.valueOf(new Random(10).nextInt()));
        user.setIntroduction("this is newly created user.");
        mongo();
        return user;
    }

    public void mongo(){
        //MongoClient mongoClient = new MongoClient();

// or
        //MongoClient mongoClient = new MongoClient( "localhost" );

// or
        //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
        /*MongoClient mongoClient = new MongoClient(
                Arrays.asList(new ServerAddress("localhost", 27017),
                        new ServerAddress("localhost", 27018),
                        new ServerAddress("localhost", 27019)));
*/
// or use a connection string
        //MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
        //mongoDbFactory.getDb().get
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));

        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("mydb");
        database.getCollection("customer").insertOne(doc);
    }

}
