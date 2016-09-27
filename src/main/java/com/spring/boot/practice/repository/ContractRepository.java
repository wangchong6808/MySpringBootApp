package com.spring.boot.practice.repository;

import com.spring.boot.practice.model.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wangchong on 9/26/16.
 */
public interface ContractRepository extends MongoRepository<Contract, String> {


}
