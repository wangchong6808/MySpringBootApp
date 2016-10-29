package com.spring.boot.practice;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.sun.javadoc.Doc;
import org.bson.Document;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by wangchong on 10/10/16.
 */
public class MongoDBTest {

    public static void main(String[] args){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/mydb");
        MongoClient client = new MongoClient(connectionString);
        MongoCollection<Document> collection = client.getDatabase("lead-management").getCollection("lead");
        collection.find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                document.put("lead_id", document.remove("_id"));
                String json = document.toJson();
                System.out.println(json);
                document = Document.parse(json);
                System.out.println(document.toJson());
            }
        });
        //org.bson.
        //BsonSerializer serializer = null;
        //Document.parse()


    }
}
