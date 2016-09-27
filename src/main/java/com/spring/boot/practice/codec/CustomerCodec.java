package com.spring.boot.practice.codec;

import com.spring.boot.practice.model.Customer;
import org.bson.*;
import org.bson.codecs.*;

import java.util.Random;

/**
 * Created by wangchong on 9/26/16.
 */
public class CustomerCodec implements CollectibleCodec<Customer> {

    private Codec<Document> documentCodec;

    public CustomerCodec(){
        documentCodec = new DocumentCodec();
    }

    public CustomerCodec(Codec<Document> codec){
        this.documentCodec = codec;
    }
    @Override
    public Customer generateIdIfAbsentFromDocument(Customer customer) {
        if (!documentHasId(customer)){
            customer.setId(String.valueOf(new Random(10).nextInt()));
        }
        return customer;
    }

    @Override
    public boolean documentHasId(Customer customer) {
        return null == customer.getId();
    }

    @Override
    public BsonValue getDocumentId(Customer customer) {
        if (!documentHasId(customer))
        {
            throw new IllegalStateException("The document does not contain an _id");
        }

        return new BsonString(customer.getId());
    }

    @Override
    public Customer decode(BsonReader bsonReader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(bsonReader, decoderContext);
        System.out.println("document "+document);
        Customer customer = new Customer();
        return customer;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Customer customer, EncoderContext encoderContext) {

    }

    @Override
    public Class<Customer> getEncoderClass() {
        return Customer.class;
    }
}
