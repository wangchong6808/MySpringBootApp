package com.daimler.otr.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LeadService {

    private static final String LEAD_COLLECTION_NAME = "lead";
    public void queryLeads(){
        //MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
        //MongoClient mongoClient = new MongoClient(connectionString);

        //MongoDatabase database = mongoClient.getDatabase("lead-management");
    }

    public void createLead(Document lead){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("lead-management");
        database.getCollection(LEAD_COLLECTION_NAME).insertOne(lead);
    }

    private String generateLeadId(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        String randomPart = String.valueOf(date.getTime()).substring(0,6);
        String formatedDate = format.format(date);
        String leadId = "L"+formatedDate+randomPart;
        return leadId;
    }
}
