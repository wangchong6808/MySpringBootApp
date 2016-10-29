package com.spring.boot.practice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.spring.boot.practice.model.Customer;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wangchong on 9/26/16.
 */
@Component
public class MongoDBClient {

    @Value("${mongodb.uri}")
    private String database_uri;

    private MongoDatabase database;

    private MongoClient client;



    public MongoDBClient(){
        if (StringUtils.hasText(database_uri)){
            MongoClientURI connectionString = new MongoClientURI(database_uri);
            client = new MongoClient(connectionString);
        }else {
            //throw new IllegalStateException("mongodb.uri is not correctly configured!");
        }
    }


    public <T> List<T> find(String collectionName, Class<T> entityClass){
        MongoDatabase database = client.getDatabase("mydb");
        MongoCollection collection = database.getCollection(collectionName);
        //collection.
        //Document myDoc = collection.find().first();
        //System.out.println(myDoc.toJson());
        return new ArrayList<T>();
    }

    public static void main(String... args) throws NoSuchMethodException, NoSuchFieldException {
        Customer customer = solution2(Customer.class);
        System.out.println(customer);

        //Field field = Customer.class.getDeclaredField("contacts");//getDeclaredFields();
        //System.out.println(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);

    }

    private static <T> T solution2(Class<T> entityClass){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/mydb");
        MongoClient client = new MongoClient(connectionString);
        MongoCollection<Document> collection = client.getDatabase("mydb").getCollection("customer");
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
        JacksonJsonParser parser = new JacksonJsonParser();
        Map<String,Object> map = parser.parseMap(myDoc.toJson());
        System.out.println();
        T t = null;
        String className = String.valueOf(map.get("_class"));
        try {
            Field id_field = ReflectionUtils.findField(entityClass,new ReflectionUtils.AnnotationFieldFilter(Id.class));
            if (id_field==null){
                throw new IllegalStateException("Id field is not found on class "+entityClass+" it should either be named _id or annotated with @Id annotation!");
            }
            Object id = map.remove("_id");
            t = deserializeFromMap(map, entityClass);
            ReflectionUtils.setField(id_field, t, id);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    private static <T> T deserializeFromMap(Map<String, Object> map, Class<T> entityClass) throws IllegalAccessException, InstantiationException {
        T t = entityClass.newInstance();
        map.forEach((key,value)->{
            Field field = ReflectionUtils.findField(entityClass, new FieldNameFieldFilter(key));
            if (field!=null){
                if (value instanceof Map){
                    try {
                        Object o = deserializeFromMap((Map)value, field.getType());
                        ReflectionUtils.setField(field, t, o);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else if (value instanceof List){
                    Type type = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                    System.out.println("---------"+((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                    List list = null;
                    try {
                        list = listToObject((List)value, Class.forName(type.getTypeName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    ReflectionUtils.setField(field,t,list);
                }else{
                    ReflectionUtils.setField(field, t, value);
                }
            }
        });
        return t;
    }

    private static <T> List listToObject(List<Object> list, Class<T> entityClass){
        List valueList = new ArrayList();
        list.forEach((obj) -> {
            if (obj instanceof Map){

                try {
                    Object o = deserializeFromMap((Map)obj,entityClass);
                    valueList.add(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                //mapToObject((Map<String, Object>)obj)
            }else if(obj instanceof List){
                valueList.add(listToObject((List)obj, List.class));
            }else {
                valueList.add(obj);
            }
        });
        return valueList;
    }

    /*private static <T> T mapToObject(Map<String, Object> map, Class<T> entityClass) throws IllegalAccessException, InstantiationException {
        T t = entityClass.newInstance();
        map.forEach((key,value)->{
            Field field = ReflectionUtils.findField(entityClass, new FieldNameFieldFilter(key));
            if (field!=null){
                if (value instanceof Map){
                    try {
                        Object o = mapToObject((Map<String, Object>)value, field.getType().getClass());
                        ReflectionUtils.setField(field, t, o);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else if (value instanceof List){
                    List list = listToObject((List)value);
                    ReflectionUtils.setField(field, t, list);
                }
            }
        });
        return t;
    }*/



    /**
     * 利用ObjectMapper做转换，limitation是Model中的对象属相不可以使用JsonProperty，否则无法正确转换
     */
    private void solution1(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/mydb");
        MongoClient client = new MongoClient(connectionString);
        MongoCollection<Document> collection = client.getDatabase("mydb").getCollection("customer");
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
        JacksonJsonParser parser = new JacksonJsonParser();
        Map<String,Object> map = parser.parseMap(myDoc.toJson());
        System.out.println();
        map.forEach((key,value)->{
            System.out.println(key+":"+value);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addHandler(new DeserializationProblemHandler(){
            @Override
            public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
                if (propertyName.equals("_id")){
                    Field field = ReflectionUtils.findField(beanOrClass.getClass(), new ReflectionUtils.AnnotationFieldFilter(Id.class));
                    if (field!=null){

                        ReflectionUtils.setField(field, beanOrClass, p.getValueAsString());
                    }else{
                        throw new IllegalStateException("field with @Id annotation is not found on "+beanOrClass.getClass());
                    }
                }else{
                    Field field = ReflectionUtils.findField(beanOrClass.getClass(), new FieldNameFieldFilter(propertyName));
                    if (field!=null){
                        ReflectionUtils.setField(field, beanOrClass, p.getValueAsString());
                    }
                }
                System.out.println("unknown property:"+propertyName+" -- " + p.getValueAsString());
                return true;
            }
        });
        try {
            Customer customer = objectMapper.readValue(myDoc.toJson(),new CustomerTypeReference());
            System.out.println(objectMapper.writeValueAsString(customer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FieldNameFieldFilter implements org.springframework.util.ReflectionUtils.FieldFilter {

        private String fieldName;

        public FieldNameFieldFilter(String fieldName){
            this.fieldName = fieldName;
        }
        @Override
        public boolean matches(Field field) {
            return field.getName().equals(fieldName);
        }
    }

    public static class JsonAnnotationFieldFilter implements ReflectionUtils.DescribedFieldFilter {
        //private final JsonProperty annotationType;

        private final String annotationValue;

        public JsonAnnotationFieldFilter(String annotationValue) {
            Assert.notNull(annotationValue, "Annotation value must not be null!");
            this.annotationValue = annotationValue;
        }

        public boolean matches(Field field) {
            JsonProperty jsonProperty = AnnotationUtils.getAnnotation(field, JsonProperty.class);
            if (jsonProperty!= null && annotationValue.equals(jsonProperty.value())){
                return true;
            }
            return false;
        }

        public String getDescription() {
            return String.format("Annotation filter for %s", new Object[]{JsonProperty.class.getName()});
        }
    }
    private static class CustomerTypeReference extends TypeReference<Customer> {
        private CustomerTypeReference() {
        }
    }
}
