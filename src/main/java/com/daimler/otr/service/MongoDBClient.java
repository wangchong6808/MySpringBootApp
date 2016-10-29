package com.daimler.otr.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Service;

/**
 * Created by wangchong on 10/11/16.
 */
@Service
public class MongoDBClient {

    private static MongoClient mongoClient;

    private static MongoClient getMongoClient(){
        if (mongoClient==null){
            synchronized (MongoDBClient.class){
                if (mongoClient==null){
                    MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
                    mongoClient = new MongoClient(connectionString);
                }
            }
        }
        return mongoClient;
    }



}
