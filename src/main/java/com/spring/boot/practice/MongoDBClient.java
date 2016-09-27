package com.spring.boot.practice;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Created by wangchong on 9/26/16.
 */
public class MongoDBClient {

    public static MongoClient getDBClient(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        return mongoClient;
    }
}
