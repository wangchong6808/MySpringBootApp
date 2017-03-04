package com.spring.boot.practice;

import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.sun.javadoc.Doc;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by wangchong on 10/10/16.
 */
public class MongoDBTest {

    static String lead1 = "{\n" +
            "  \"_id\": \"L16101598955\",\n" +
            "  \"person\": {\n" +
            "    \"first_name\": \"名\",\n" +
            "    \"last_name\": \"姓\",\n" +
            "    \"gender\": \"male\",\n" +
            "    \"mobile\": \"13911911999\",\n" +
            "    \"tel\": \"66668888\",\n" +
            "    \"wechat\": \"zhang123\",\n" +
            "    \"company\": \"Daimler\",\n" +
            "    \"address\": \"东直门\"\n" +
            "  },\n" +
            "  \"tag\": [\n" +
            "    \"土豪\",\n" +
            "    \"煤老板\"\n" +
            "  ],\n" +
            "  \"lead_source\": \"MB Website\",\n" +
            "  \"lead_type\": \"MB Website Retail\",\n" +
            "  \"channel\": \"walk_in\",\n" +
            "  \"grade\": \"A\",\n" +
            "  \"current_sales_consultant_id\": \"SC1001\",\n" +
            "  \"dealer_id\" : \"DL211\",\n" +
            "  \"tag\" : [\"土豪\",\"煤老板\"],\n" +
            "  \"lead_source\":\"MB Website\",\n" +
            "  \"lead_type\":\"MB Website Retail\",\n" +
            "  \"version\": 1,\n" +
            "  \"comments\": \"备注信息\",\n" +
            "  \"activities\" : [\n" +
            "    {\n" +
            "      \"_id\" : \"20161101001\",\n" +
            "      \"activity_type\" : \"visit\",\n" +
            "      \"occur_time\" : \"2016-11-01 12:00:00\",\n" +
            "      \"related_sc_id\" : \"SC001\",\n" +
            "      \"is_appointed\" : true,\n" +
            "      \"is_first_visit\" : true,\n" +
            "      \"arrival_time\" : \"2016-11-01 10:00:00\",\n" +
            "      \"leave_time\" : \"2016-11-01 13:00:00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"_id\" : \"20161101002\",\n" +
            "      \"activity_type\" : \"visit\",\n" +
            "      \"occur_time\" : \"2016-11-01 12:00:00\",\n" +
            "      \"related_sc_id\" : \"SC001\",\n" +
            "      \"is_appointed\" : true,\n" +
            "      \"is_first_visit\" : false,\n" +
            "      \"arrival_time\" : \"2016-11-01 10:00:00\",\n" +
            "      \"leave_time\" : \"2016-11-01 13:00:00\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
    static String lead2 = "{\n" +
            "  \"_id\": \"L16101598956\",\n" +
            "  \"person\": {\n" +
            "    \"first_name\": \"名\",\n" +
            "    \"last_name\": \"姓\",\n" +
            "    \"gender\": \"male\",\n" +
            "    \"mobile\": \"13911911990\",\n" +
            "    \"tel\": \"66668888\",\n" +
            "    \"wechat\": \"zhang123\",\n" +
            "    \"company\": \"Daimler\",\n" +
            "    \"address\": \"东直门\"\n" +
            "  },\n" +
            "  \"tag\": [\n" +
            "    \"土豪\",\n" +
            "    \"煤老板\"\n" +
            "  ],\n" +
            "  \"lead_source\": \"MB Website\",\n" +
            "  \"lead_type\": \"MB Website Retail\",\n" +
            "  \"channel\": \"walk_in\",\n" +
            "  \"grade\": \"A\",\n" +
            "  \"current_sales_consultant_id\": \"SC1001\",\n" +
            "  \"dealer_id\" : \"DL211\",\n" +
            "  \"tag\" : [\"土豪\",\"煤老板\"],\n" +
            "  \"lead_source\":\"MB Website\",\n" +
            "  \"lead_type\":\"MB Website Retail\",\n" +
            "  \"version\": 1,\n" +
            "  \"comments\": \"备注信息\",\n" +
            "  \"activities\" : [\n" +
            "    {\n" +
            "      \"_id\" : \"20161101001\",\n" +
            "      \"activity_type\" : \"visit\",\n" +
            "      \"occur_time\" : \"2016-11-01 12:00:00\",\n" +
            "      \"related_sc_id\" : \"SC002\",\n" +
            "      \"is_appointed\" : true,\n" +
            "      \"is_first_visit\" : true,\n" +
            "      \"arrival_time\" : \"2016-11-01 10:00:00\",\n" +
            "      \"leave_time\" : \"2016-11-01 13:00:00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"_id\" : \"20161101002\",\n" +
            "      \"activity_type\" : \"visit\",\n" +
            "      \"occur_time\" : \"2016-11-01 12:00:00\",\n" +
            "      \"related_sc_id\" : \"SC001\",\n" +
            "      \"is_appointed\" : true,\n" +
            "      \"is_first_visit\" : true,\n" +
            "      \"arrival_time\" : \"2016-11-01 10:00:00\",\n" +
            "      \"leave_time\" : \"2016-11-01 13:00:00\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    public static void main(String[] args){
        //createData();
        queryByArrayData();
        //count();
        //org.bson.
        //BsonSerializer serializer = null;
        //Document.parse()


    }

    private static MongoCollection<Document> getCollection(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(connectionString);
        return client.getDatabase("lead-management").getCollection("lead");
    }

    private static void createData(){
        MongoCollection<Document> collection = getCollection();
        Document leadDoc1 = Document.parse(lead1);
        Document leadDoc2 = Document.parse(lead2);
        collection.insertOne(leadDoc1);
        collection.insertOne(leadDoc2);
        collection.find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                document.put("lead_id", document.remove("_id"));
                String json = document.toJson();
                System.out.println(json);
            }
        });
    }

    private static void queryByArrayData(){
        MongoCollection<Document> collection = getCollection();
        /*List<Bson> filters = Arrays.asList(new Bson[]{Aggregates.project(Projections.fields(include("activities.arrival_time", "dealer_id"))),
                Aggregates.match(and(Filters.eq("dealer_id", "DL10000"),Filters.gte("activities.arrival_time", "1744701000000"))),
                Aggregates.unwind("$activities"),
                Aggregates.match(Filters.gte("activities.arrival_time", "1744701000000"))});*/
        Bson filter = and(Filters.eq("dealer_id", "DL10000"),Filters.gte("activities.arrival_time", "1744701000000"));
        List<Bson> filters = Arrays.asList(new Bson[]{
                Aggregates.match(filter),
                Aggregates.unwind("$activities"),
                Aggregates.match(filter)});

                //Aggregates.project(Projections.include("activities.arrival_time")),
                //Aggregates.match(Filters.gte("activities", "1744701000000"))});
                //,Filters.and(, )
                //Filters.gte("arrival_time", "1744701000000")});
        //Bson filter = ;
        //Aggregates.project();
       // filter = Filters.and(filter, Filters.gte("activities.arrival_time", "1744701000000"));
        Counter counter = new Counter();
        collection.aggregate(filters).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println(document.toJson());
            }
        });
        //.projection(Projections.include("activities"))
                //.projection(Projections.elemMatch("activities.arrival_time"))
                /*.map(document -> {
                    List list = (List)document.get("activities");
                    if (list!=null){
                        return list.size();
                    }
                    return 0;
                })*/
                /*.map(new Function<Document, Integer>() {

                    @Override
                    public Integer apply(Document document) {
                        List list = (List)document.get("activities");
                        if (list!=null){
                            return list.size();
                        }
                        return 0;
                    }
                })*/

        System.out.println("count === "+counter.getCount());
        /*System.out.println("---------------");
        collection.find(filter).projection(Projections.include("activities")).projection(Projections.elemMatch("activities", Filters.eq("related_sc_id","SC002"))).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println(document.toJson());
            }
        });*/
    }



    private static void count(){
        MongoCollection<Document> collection = getCollection();
        Bson filter = Filters.eq("current_sales_consultant_id", "SC1001");
        filter = and(filter, Filters.eq("activities.related_sc_id", "SC003"));
        System.out.println(collection.count(filter));
    }
}

class Counter{
    int count = 0;
    public void add(int num){
        count = count+num;
    }

    public int getCount(){
        return count;
    }
}
